package rml.args.manager

import scala.reflect.io.File

import rml.args.domain.Func
import rml.args.reader.ConfReader

object DefaultSetup {

  def apply(prefix: String) = {
    
    FunctionRegister("help")            = HelpFunctions().help
    FunctionRegister("")                = FunctionRegister("help")
    
    FunctionRegister("conf")            = Func{ 
      println("Default config files:\nFound  File")
      ConfReader.defaultConfFilePaths(prefix).foreach(p => println("[" + (if(File(p).exists) "X" else " ") + "]    " + p))
    }
  }
}