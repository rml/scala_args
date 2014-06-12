package rml.args.run

import java.io.File
import scala.reflect.io.Path.string2path

import com.typesafe.scalalogging.slf4j.LazyLogging

import rml.args.arg.{/ => /}
import rml.args.arg.Func
import rml.args.config.reader.ConfReader
import rml.args.help.DefaultHelpSetup
import rml.args.help.HelpFunctions
import rml.args.jline.DefaultJlineSetup
import rml.args.logging.DefaultLogSetup
import rml.args.arg.function.FunctionOrigin
import rml.args.register.@@

object DefaultSetup extends LazyLogging {

  implicit val origin = FunctionOrigin("DefaultSetup")

  def apply(prefix: String, systemPrefix: String = "@") = {

    DefaultLogSetup(prefix, systemPrefix)
    
    logger.debug("Default setup with prefix '{}'", prefix)
    
    DefaultJlineSetup(prefix, systemPrefix)
    
    DefaultHelpSetup(prefix, systemPrefix)
    
    
    @@(systemPrefix + "conf", "Show configuration settings") -->
    Func{ 

      val fullConfig = ConfReader(Array[String](), prefix, "conf")
      HelpFunctions().printFullConf(fullConfig)
    }
    

    @@(systemPrefix + "conf files", "Show configuration files") -->
    Func{ 

      println("Default config files:\nFound  File")
      ConfReader.defaultConfFilePaths(prefix).foreach(p => println("[" + (if(new File(p).exists) "X" else " ") + "]    " + p))
    }
    
    
    @@(systemPrefix + "version", "Show scala_args version") -->
    Func{
      
      val scalaArgsPackage = getClass.getPackage
      
      println(scalaArgsPackage.getImplementationTitle + ": " + scalaArgsPackage.getImplementationVersion)
    }
  }
}