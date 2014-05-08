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

  def printArg(arg: InputArg[_], indent: String, suffix: String): Unit = {

    printf("%-15s %s\n", "  ", arg.key, arg.showdesc)    	  
  }
    	
  /**
   * Print detailed information about one function (temporary solution)
   */
  def printFunctionDescription(name: String, func: Function[_], withOrigin: Boolean = false) = {

    val indent = " " * 5
    
    val nameAndOrigin = name + (if(withOrigin) " (Origin: %s)".format(func.origin.origin) else "")

    println(nameAndOrigin)
    println("_" * nameAndOrigin.length)
    println("\nDescription:")
    println(indent + func.description)
    println("\nArguments:")
    
    for(arg <- func.inputArgs) {
      printArg(arg, indent, "")
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