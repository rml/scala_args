package rml.args.manager

import scala.collection.JavaConversions.seqAsJavaList
import scala.reflect.io.File
import scala.reflect.io.Path.string2path
import jline.TerminalFactory
import jline.console.ConsoleReader
import jline.console.completer.StringsCompleter
import rml.args.domain.{/ => /}
import rml.args.domain.Func
import rml.args.reader.ConfReader
import jline.console.completer.ArgumentCompleter
import jline.console.completer.AggregateCompleter

object DefaultSetup {

  implicit val origin = FunctionOrigin("DefaultSetup")

  def apply(prefix: String) = {
    
    FunctionRegister("help") = HelpFunctions().help -- "Display this help function"
      
    FunctionRegister("") = 
      / / "Interactive mode" /
      Func{ 

        try {
          val completers = for{cmd <- FunctionRegister.commands("-")
            functionDefinition = FunctionRegister(cmd.split("-").toList)
          } yield {
            new ArgumentCompleter(new StringsCompleter(cmd), new StringsCompleter(functionDefinition.args.map("-" + _.key)))
          }
          
          val console = new ConsoleReader()
          console.setPrompt(prefix + "> ")
          console.addCompleter(new AggregateCompleter(completers))

          var line = console.readLine()
          while (line != null) {
            console.println(line)
            DefaultRunner(line.split("""\s+|-"""), prefix)
            line = console.readLine()
          }          
        } finally {

          TerminalFactory.get().restore()
        }
    }

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