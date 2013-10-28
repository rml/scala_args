package rml.args.manager
import rml.args.reader.ConfReader
import rml.args.exceptions.IllegalArgException
import rml.args.exceptions.FunctionNotFoundException

object DefaultRunner {

  def apply(args: Array[String], prefix: String): Unit = {
    
    try {
      val fullConfig = ConfReader(args, prefix)
//      HelpFunctions().printFullConf(fullConfig)
      FunctionRegister.run(fullConfig)
      System.exit(0)
    } catch {
      case iae: IllegalArgException => println(iae.getMessage); if(args.contains("-stack")) iae.printStackTrace()
      case fnfe: FunctionNotFoundException => println("Function '" + fnfe.function.head + "' not found\nDid you mean one of these?")
                 HelpFunctions().printFunctionList(fnfe.function.head)
    }
    System.exit(1)
  }
}