package rml.args.manager

import rml.args.domain.Func
import rml.args.argdecorator.Opt
import rml.args.argmapper.PositionalArg
import rml.args.arg.Arg
import java.io.PrintStream
import rml.args.arg.MultiArg
import rml.args.domain.FunctionDefinition
import rml.args.conversions.strings.Strings0
import rml.args.arg.Flag

case class HelpFunctions(out: PrintStream = System.out) {

  /**
   * Print list of functions, that match the filter
   */
  def printFunctionList(filter: String) = {
    
    for((name, func) <- FunctionRegister.list(filter) if !name.isEmpty){
      out.format("%-15s %s\n", name, func.description)
    }
  }

  /**
   * Print detailed information about one function (temporary solution)
   */
  def printFunctionDescription(name: String, func: FunctionDefinition[_]) = {

    val format = "%-" + (name.length + 5) + "s     %-10s %s\n"
    printf(format, name, func.description, "")

    def printArg(arg: Arg[_], suffix: String): Unit = {
      val suffix = arg match {
        case parg: PositionalArg[_] => " (" + parg.pos + ")"
        case Opt(parg: PositionalArg[_]) => " (" + parg.pos + ")"
        case marg: MultiArg[_] => return for(a <- marg.args){ printArg(a, "") }
        case _ => ""
      }

      printf(format, "  ", arg.key + suffix, arg.showdesc)    	  
    }
    	
    for(arg <- func.args) {
      printArg(arg, "")
    }
  }
  
  /**
   * Print information about registered functions to the console
   */
  def help = Func(Strings0("-"), Flag("list") ~ "l"){ (filter, list) =>

    val functionRegistered = FunctionRegister.isRegistered(filter)
    
    if(list || !functionRegistered){
      printFunctionList(filter.mkString(" "))
    } else {
      printFunctionDescription(filter.mkString(" "), FunctionRegister.get(filter))
    }
 
  }
}