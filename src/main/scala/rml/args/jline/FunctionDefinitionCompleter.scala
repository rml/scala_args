package rml.args.jline

import scala.collection.JavaConversions.seqAsJavaList

import jline.console.completer.Completer
import jline.console.completer.StringsCompleter
import rml.args.config.reader.CommandlineArgReader
import rml.args.register.FunctionRegister
import rml.args.arg.restriction.SetRestriction

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

    val lastArgKey = functionArgs.lastArg

    val argToComplete = lastArgKey != ""

    val lastArgValues = if(argToComplete) functionArgs.args(lastArgKey) else List[String]()
    val noArgValues = lastArgValues.isEmpty
    val editingValue = !buffer.endsWith(" ")
    
    if(functionArgs.trailingDash) { // new arg, taken from remainingArgs

      val argsCompleter = new StringsCompleter(remainingArgs.map("-" + _).toList.sorted)
      
      if(argsCompleter.complete("-", cursor, candidates) == 0){
        return buffer.size - 1
      }

    } else if(argToComplete && noArgValues && editingValue) { // new arg, taken from remainingArgs

      val args = if(allArgs.contains(lastArgKey)) remainingArgs + lastArgKey else remainingArgs
      val argsCompleter = new StringsCompleter(args.map("-" + _).toList.sorted)
      
      if(argsCompleter.complete("-" + lastArgKey, cursor, candidates) == 0){
        return buffer.size - lastArgKey.size - 1
      }
      
    } else if(argToComplete && functionDefinition.inputArg.contains(lastArgKey)) {
      
      val argDef = functionDefinition.inputArg(lastArgKey)

      argDef.restriction match {
        
        case setRestriction: SetRestriction =>
            val values = setRestriction.allowed
            val valuesCompleter = new StringsCompleter(values.toList.sorted)
            val part = if(editingValue) lastArgValues.reverse.headOption.getOrElse("") else ""
            	
            	if(valuesCompleter.complete(part, cursor, candidates) == 0){
            		return buffer.size - part.size
            	}
        case _ => -1
      }
    }
    
    -1
  }
}