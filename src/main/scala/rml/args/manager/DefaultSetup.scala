package rml.args.manager

import scala.reflect.io.File
import scala.reflect.io.Path.string2path
import rml.args.domain.{/ => /}
import rml.args.domain.Func
import rml.args.jline.JLineConsole
import rml.args.reader.ConfReader
import com.typesafe.scalalogging.slf4j.Logging
import rml.args.conversions.map.AnEnum

object DefaultSetup extends Logging {

  import logger._
  implicit val origin = FunctionOrigin("DefaultSetup")

  def apply(prefix: String) = {

    info("Default setup with prefix '{}'", prefix)
    
    FunctionRegister("") = Func{ JLineConsole.open(prefix.replaceAll("_$", ""), prefix) } -- "Interactive mode"

    FunctionRegister("help") = HelpFunctions().help -- "Display this help function"

    FunctionRegister("loglevel") = Func(AnEnum("-", LogLevel)){ ll =>
      LoggerSetup.setLoglevel(ll)
    }

    FunctionRegister("conf") = 
      / / "Show configuration settings" /
      Func{ 

      val fullConfig = ConfReader(Array[String](), prefix, "conf")
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