package rml.args.jline

import com.typesafe.scalalogging.{LazyLogging => Logging}
import jline.TerminalFactory
import jline.console.ConsoleReader
import jline.console.completer.{ArgumentCompleter, StringsCompleter}
import jline.console.history.FileHistory
import rml.args.exceptions.IllegalArgException
import rml.args.register.FunctionRegister
import rml.args.run.DefaultRunner

import java.io.File
import scala.jdk.CollectionConverters._

object JLineConsole extends Logging {

  import logger._

  def open(prompt: String, prefix: String): Unit = {

    def exit(history: FileHistory): Unit = {

      history.flush()
      System.exit(0)
    }

    try {

      val mc = new FunctionDefinitionCompleter()

      val completers = for {
        cmd <- FunctionRegister.commands("_")
        functionDefinition = FunctionRegister(cmd.split("_").toList)
      } yield {
        new ArgumentCompleter(
          new StringsCompleter(cmd),
          new StringsCompleter(
            functionDefinition.args
              .flatMap(_.inputArgs)
              .map("-" + _.key)
              .asJavaCollection
          )
        )
      }

      val console = new ConsoleReader()
      console.setPrompt(prompt + "> ")
      console.addCompleter(mc)

      val history = new FileHistory(
        new File(
          System.getProperty("user.home"),
          "." + prefix.toLowerCase + "history"
        )
      )
      console.setHistory(history)

      var line = console.readLine()
      while (line != null) {

        line match {
          case "exit" => exit(history)
          case l =>
            try {
              DefaultRunner(l, prefix)
            } catch {
              case iae: IllegalArgException => error(iae.getMessage)
            }
        }

        line = console.readLine()
      }

    } finally {
      TerminalFactory.get().restore()
    }
  }

}
