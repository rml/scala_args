package rml.args.jline

import scala.collection.JavaConversions.seqAsJavaList
import jline.TerminalFactory
import jline.console.ConsoleReader
import jline.console.completer.ArgumentCompleter
import jline.console.completer.StringsCompleter
import rml.args.manager.DefaultRunner
import rml.args.manager.FunctionRegister
import jline.console.history.FileHistory
import java.io.File

object JLineConsole {

  def open(prompt: String, prefix: String) = {

    def exit(history: FileHistory) = {

      history.flush()
      System.exit(0)
    }
    
    try {
      
      val mc = new FunctionDefinitionCompleter()
      
      val completers = for{cmd <- FunctionRegister.commands("_")
        functionDefinition = FunctionRegister(cmd.split("_").toList)
      } yield {
        new ArgumentCompleter(new StringsCompleter(cmd), new StringsCompleter(functionDefinition.args.map("-" + _.key)))
      }
      
      val console = new ConsoleReader()
      console.setPrompt(prompt + "> ")
      console.addCompleter(mc)
      
      val history = new FileHistory(new File(System.getProperty("user.home"), "." + prefix.toLowerCase + "history"))
      console.setHistory(history) 

      var line = console.readLine()
      while (line != null) {
        
        line match {
          case "exit" => exit(history)
          case l => DefaultRunner(l, prefix)
        }
        
        line = console.readLine()
      }
      
    } finally {
      TerminalFactory.get().restore()
    }
  }
  
}