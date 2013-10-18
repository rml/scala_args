package rml.args.manager
import rml.args.reader.ArgReader
import rml.args.exceptions.IllegalArgException
import rml.args.exceptions.FunctionNotFoundException

object DefaultRunner {

  def apply(args: Array[String], prefix: String): Unit = {
    
    try {
      val functionArguments = ArgReader(args, prefix)
      FunctionRegister.run(functionArguments)
      System.exit(0)
    } catch {
      case iae: IllegalArgException => println(iae.getMessage)
      case fnfe: FunctionNotFoundException => println("Function '" + fnfe.function.head + "' not found\nDid you mean one of these?")
                 HelpFunctions().printFunctionList(fnfe.function.head)
    }
    System.exit(1)
  }
}