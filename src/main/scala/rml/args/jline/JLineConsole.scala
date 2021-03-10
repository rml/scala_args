package rml.args.jline

import com.typesafe.scalalogging.{LazyLogging => Logging}
import org.jline.reader.impl.history.DefaultHistory
import org.jline.reader.{History, LineReaderBuilder}
import rml.args.exceptions.IllegalArgException
import rml.args.run.DefaultRunner

object JLineConsole extends Logging {

  import logger._

  private val completer = new FunctionDefinitionCompleter()

  private val lineReader = LineReaderBuilder
    .builder()
    .history(new DefaultHistory())
    .completer(completer)
    .build()

  def open(prompt: String, prefix: String): Unit = {

    try {

      var line = lineReader.readLine(prompt + "> ")
      while (line != null) {

        line match {
          case "exit" =>
            lineReader.getHistory.save()
            return
          case l =>
            try {
              DefaultRunner(l, prefix)
            } catch {
              case iae: IllegalArgException => error(iae.getMessage)
            }
        }

        line = lineReader.readLine(prompt + "> ")
      }

    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
