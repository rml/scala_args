package rml.args.jline

import com.typesafe.scalalogging.LazyLogging
import org.jline.reader.{Candidate, Completer, LineReader, ParsedLine}
import rml.args.arg.restriction.SetRestriction
import rml.args.config.reader.CommandlineArgReader
import rml.args.register.FunctionRegister

import java.util

class FunctionDefinitionCompleter extends Completer with LazyLogging {

  override def complete(
      reader: LineReader,
      line: ParsedLine,
      candidates: util.List[Candidate]
  ): Unit = {

    val commands = FunctionRegister.commands().toSet
    val input = line.line()

    if (input.trim.isEmpty) {
      FunctionRegister
        .commands()
        .map(c => c.split(" ")(0))
        .distinct
        .foreach(c => candidates.add(new Candidate(c)))
    } else {
      commands
        .collect {
          case command if command startsWith input => command.split(" ")
        }
        .collect {
          case parts if parts.length >= line.wordIndex() =>
            parts(line.wordIndex())
        }
        .foreach(c => candidates.add(new Candidate(c)))
    }

    if (input.trim.nonEmpty) {
      getArgs(input) foreach (cand => candidates.add(new Candidate(cand)))
    }
  }

  private def getArgs(buffer: String): List[String] = {

    val baseCmd = buffer.trim
    val checkCmd =
      baseCmd + (if (baseCmd.endsWith("-") && !baseCmd.endsWith("--")) "-"
                 else "")
    val functionArgs = CommandlineArgReader(checkCmd)
    val key = FunctionRegister.findLongestMatching(
      functionArgs.func :: functionArgs.subfuncs
    )

    if (key.isEmpty) {
      return Nil
    }

    val functionDefinition = FunctionRegister.get(key.get)
    val allArgs = functionDefinition.args.flatMap(_.inputArgs).map(_.key).toSet
    val usedArgs = functionArgs.args.keySet
    val remainingArgs = allArgs -- usedArgs

    val lastArgKey = functionArgs.lastArg

    val argToComplete = lastArgKey != ""

    val lastArgValues =
      if (argToComplete) functionArgs.args(lastArgKey) else List[String]()
    val noArgValues = lastArgValues.isEmpty
    val editingValue = !buffer.endsWith(" ")

    if (functionArgs.trailingDash) { // new arg, taken from remainingArgs
      remainingArgs.map("-" + _).toList
    } else if (argToComplete && noArgValues && editingValue) { // new arg, taken from remainingArgs
      val args =
        if (allArgs.contains(lastArgKey)) remainingArgs + lastArgKey
        else remainingArgs
      args.map("-" + _).toList
    } else if (
      argToComplete && functionDefinition.inputArg.contains(lastArgKey)
    ) {
      val argDef = functionDefinition.inputArg(lastArgKey)
      argDef.restriction match {
        case setRestriction: SetRestriction =>
          val values = setRestriction.allowed
          values.toList
        case _ =>
          Nil
      }
    } else {
      Nil
    }
  }
}
