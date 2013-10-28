package rml.args.manager

import scala.reflect.io.File

import rml.args.domain.Func
import rml.args.reader.ConfReader

import rml.args.domain./

object DefaultSetup {

  implicit val origin = FunctionOrigin("DefaultSetup")

  def apply(prefix: String) = {
    
    FunctionRegister("help") = HelpFunctions().help -- "Display this help function"
      
    FunctionRegister("") = FunctionRegister("help") -- "Display this help function"

    FunctionRegister("conf") = 
      / / "Show configuration settings" /
      Func{ 

      val fullConfig = ConfReader(Array[String](), prefix)
      HelpFunctions().printFullConf(fullConfig)
    }

    FunctionRegister("conf"::"files"::Nil) = 
      / / "Show configuration files" /
      Func{ 
      println("Default config files:\nFound  File")
      ConfReader.defaultConfFilePaths(prefix).foreach(p => println("[" + (if(File(p).exists) "X" else " ") + "]    " + p))
    }
  }
}