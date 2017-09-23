package rml.args.help

import java.io.PrintStream
import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.Func
import rml.args.arg.FuncArg
import rml.args.arg.InputArg
import rml.args.config.FullConfig
import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.conversions.strings.Strings0
import rml.args.arg.special.Flag

case class HelpFunctions(out: PrintStream = System.out) extends LazyLogging {

  implicit val origin = FunctionOrigin(getClass)
  
  /**
   * Print list of functions, that match the filter
   */
  def printFunctionList(filter: String) = {
    
    for((name, func) <- FunctionRegister.list(filter) if !name.isEmpty){
      out.format("%-15s %s\n", name, func.description)
    }
  }

  def printArg(arg: InputArg[_], indent: String, suffix: String): Unit = {

    out.printf("%-1s -%s %s ** %s **\n", "" , arg.key, arg.argState.aliases.map("-" + _).mkString("(", ", ", ")"), arg.typeInfo)    	  
    out.printf("%-3s %-20s \n\n", "", arg.description)
  }
    	
  /**
   * Print detailed information about one function (temporary solution)
   */
  def printFunctionDescription(name: String, func: FuncArg[_], withOrigin: Boolean = false) = {

    val indent = " " * 2
    
    val nameAndOrigin = name + (if(withOrigin) " (defined in %s)".format(func.origin.origin) else "")

    out.println(nameAndOrigin)
//    out.println("_" * nameAndOrigin.length)
    out.println()
    out.println(indent + func.description)
    out.println("\nArguments:\n")
    
    for(arg <- func.inputArgs) {
      printArg(arg, indent, "")
    }
  }
  
  /**
   * Print information about registered functions to the console
   */
  def help = Func(
      
      Strings0("filter") ~ "-" -- "Show only functions containing ... ", 
      Flag("list") ~ "l" -- "Show list of functions", 
      Flag("orig") ~ "origin" -- "Show function origin"
      
  ){ (filter, list, withOrigin) =>

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