package rml.args.manager
import rml.args.reader.ArgReader
import rml.args.exceptions.IllegalArgException

object DefaultRunner {

  def apply(args: Array[String], prefix: String): Unit = {
    
    try {
      val functionArguments = ArgReader(args, prefix)
      FunctionRegister.run(functionArguments)
      System.exit(0)
    } catch {
      case iae: IllegalArgException => println(iae.getMessage)
    }
    System.exit(1)
  }
}