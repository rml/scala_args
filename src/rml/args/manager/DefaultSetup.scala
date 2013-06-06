package rml.args.manager
import rml.args.domain.Func
import rml.args.reader.ArgReader
import scala.reflect.io.File

object DefaultSetup {

  def apply(prefix: String) = {
    
    FunctionRegister("help")            = FunctionRegister.help
    FunctionRegister("")                = FunctionRegister("help")
    
    FunctionRegister("conf")            = Func{ 
      println("Default config files:\nFound  File")
      ArgReader.defaultConfFilePaths(prefix).foreach(p => println("[" + (if(File(p).exists) "X" else " ") + "]    " + p))
    }
  }
}