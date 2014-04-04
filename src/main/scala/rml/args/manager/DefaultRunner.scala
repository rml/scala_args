package rml.args.manager
import rml.args.reader.ConfReader
import rml.args.exceptions.IllegalArgException
import rml.args.exceptions.FunctionNotFoundException
import rml.args.domain.FullConfig

object DefaultRunner {

  def apply(args: Array[String], prefix: String): Unit = apply(ConfReader(args, prefix, "conf"), prefix)
    
  def apply(args: String, prefix: String): Unit = apply(ConfReader(args, prefix, "conf"), prefix)
    
  def apply(fullConfig: FullConfig, prefix: String): Unit = {
    
    try {
      FunctionRegister.run(fullConfig)
    } catch {
      case iae: IllegalArgException => println(iae.getMessage); if(fullConfig.args.contains("-stack")) iae.printStackTrace()
      case fnfe: FunctionNotFoundException => println("Function '" + fnfe.function.head + "' not found\nDid you mean one of these?")
                 HelpFunctions().printFunctionList(fnfe.function.head)
    }
  }
}