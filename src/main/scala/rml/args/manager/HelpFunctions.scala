package rml.args.manager

import java.io.PrintStream
import rml.args.arg.Arg
import rml.args.arg.Flag
import rml.args.argdecorator.Opt
import rml.args.argmapper.PositionalArg
import rml.args.conversions.strings.Strings0
import rml.args.domain.FullConfig
import rml.args.domain.Func
import rml.args.domain.Function
import rml.args.arg.InputArg
import rml.args.argdecorator.Opt

case class HelpFunctions(out: PrintStream = System.out) {

  implicit val origin = FunctionOrigin("HelpFunctions")
  
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
  def printFunctionDescription(name: String, func: Function[_], withOrigin: Boolean = false) = {

    val format = "%-" + (name.length + 5) + "s     %-10s %s\n"
    printf(format, name, func.description, "")

    def printArg(arg: InputArg[_], suffix: String): Unit = {
      val suffix = arg match {
        case parg: PositionalArg[_] => " (" + parg.pos + ")"
//        case Opt(parg: PositionalArg[_]) => " (" + parg.pos + ")"
        case marg: Function[_] => return for(a <- marg.inputArgs){ printArg(a, "") }
        case _ => ""
      }

      printf(format, "  ", arg.key + suffix, arg.showdesc)    	  
    }
    	
    for(arg <- func.inputArgs) {
      printArg(arg, "")
    }
    
    if(withOrigin){
      println()
      println("Origin: " + func.origin.origin)
    }
  }
  
  /**
   * Print information about registered functions to the console
   */
  def help = Func(Strings0("-"), Flag("list") ~ "l", Flag("orig") ~ "origin"){ (filter, list, withOrigin) =>

    val functionRegistered = FunctionRegister.isRegistered(filter)
    
    if(list || !functionRegistered){
      printFunctionList(filter.mkString(" "))
    } else {
      printFunctionDescription(filter.mkString(" "), FunctionRegister.get(filter), withOrigin)
    }
  }
  
  def printFullConf(fullConfig: FullConfig) = {
    
    val allArgs = for{config <- fullConfig.allConfigs
      (key, value) <- config.args
    } yield {
      (key, value, config.origin)
    }
    
    for{(key, values) <- allArgs.groupBy(_._1)}{
      println(key)
      for((k, v, o) <- values){
        println(v.mkString("  ", ", ", " (" + o + ")"))
      }
    }
    
  }
}