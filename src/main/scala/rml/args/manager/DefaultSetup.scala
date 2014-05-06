package rml.args.manager

import scala.reflect.io.File
import scala.reflect.io.Path.string2path
import com.typesafe.scalalogging.slf4j.{LazyLogging => Logging}
import rml.args.conversions.map.AnEnum
import rml.args.conversions.strings.JString
import rml.args.domain.{/ => /}
import rml.args.domain.Func
import rml.args.jline.JLineConsole
import rml.args.reader.ConfReader
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.Strings
import rml.args.conversions.strings.Strings0

object DefaultSetup extends Logging {

  import logger._
  implicit val origin = FunctionOrigin("DefaultSetup")

  def apply(prefix: String, systemPrefix: String = "@") = {

    info("Default setup with prefix '{}'", prefix)

    LoggerSetup.setPattern("%-5level %message%n")

    LoggerSetup.setLoglevel(LogLevel.INFO)
    
    FunctionRegister("") = Func{ JLineConsole.open(prefix.replaceAll("_$", ""), prefix) } -- "Interactive mode"

    FunctionRegister("help") = HelpFunctions().help -- "Display this help function"

    FunctionRegister(systemPrefix + "loglevel") = Func(AnEnum("-", LogLevel)){ ll =>
      LoggerSetup.setLoglevel(ll)
    }

    FunctionRegister(systemPrefix + "set") = Func(Strings0("-")){ envVar =>
      
      import scala.collection.JavaConversions._
      
      envVar match {
        case Nil => System.getProperties().foreach{ case(k,v) => println(s"$k: $v")}
        case key :: Nil => error("Value for key $key missing")
        case key :: value => System.setProperty(prefix.toLowerCase.replaceAll("_", ".") + key, value.mkString(" "))
      }
    }

    FunctionRegister(systemPrefix + "logpattern") = Func(JString("-")){ pattern =>
      LoggerSetup.setPattern(pattern)
    }

    FunctionRegister(systemPrefix + "conf") = 
      / / "Show configuration settings" /
      Func{ 

      val fullConfig = ConfReader(Array[String](), prefix, "conf")
      HelpFunctions().printFullConf(fullConfig)
    }

    FunctionRegister(systemPrefix + "conf"::"files"::Nil) = 
      / / "Show configuration files" /
      Func{ 
      println("Default config files:\nFound  File")
      ConfReader.defaultConfFilePaths(prefix).foreach(p => println("[" + (if(File(p).exists) "X" else " ") + "]    " + p))
    }
  }
}