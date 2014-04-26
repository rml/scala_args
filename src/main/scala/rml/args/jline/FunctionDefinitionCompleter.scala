package rml.args.jline

import scala.collection.JavaConversions.seqAsJavaList

import jline.console.completer.Completer
import jline.console.completer.StringsCompleter
import rml.args.manager.FunctionRegister
import rml.args.reader.CommandlineArgReader

class FunctionDefinitionCompleter extends Completer {

  def complete(buffer: String, cursor: Int, candidates: java.util.List[CharSequence]): Int = {
    
    val commandsCompleter = new StringsCompleter(FunctionRegister.commands(" "))

    if(commandsCompleter.complete(buffer, cursor, candidates) == 0){
      return 0
    }
    
    val baseCmd = buffer.trim
    val checkCmd = baseCmd + (if(baseCmd.endsWith("-") && !baseCmd.endsWith("--")) "-" else "") 
    val functionArgs = CommandlineArgReader(checkCmd)
    val key = FunctionRegister.findLongestMatching(functionArgs.func :: functionArgs.subfuncs)
    
    if(!key.isDefined){
      return -1
    }
    
    val functionDefinition = FunctionRegister.get(key.get)
    val allArgs = functionDefinition.args.flatMap(_.inputArgs).map(_.key).toSet
    val usedArgs = functionArgs.args.keySet
    val remainingArgs = allArgs -- usedArgs

    val lastArg = functionArgs.lastArg
    
    if(functionArgs.trailingDash) {
      
      val argsCompleter = new StringsCompleter(remainingArgs.map("-" + _).toList.sorted)
      
      if(argsCompleter.complete("-", cursor, candidates) == 0){
        return buffer.size - 1
      }

    } else if(lastArg != "" && functionArgs.args(lastArg).isEmpty && !buffer.endsWith(" ")) {

      val args = if(allArgs.contains(lastArg)) remainingArgs + lastArg else remainingArgs
      val argsCompleter = new StringsCompleter(args.map("-" + _).toList.sorted)
      
      if(argsCompleter.complete("-" + lastArg, cursor, candidates) == 0){
        return buffer.size - lastArg.size - 1
      }
    }
    
    -1
  }
}