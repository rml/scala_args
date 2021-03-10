package rml.args.run

import com.typesafe.scalalogging.{LazyLogging => Logging}
import rml.args.config.FullConfig
import rml.args.config.reader.ConfReader
import rml.args.exceptions.{FunctionNotFoundException, IllegalArgException}

import scala.util.{Failure, Success}

object DefaultRunner extends Logging {

  import logger._

  def apply(args: Array[String], prefix: String): Unit =
    run(ConfReader(args, prefix, "conf"), prefix)

  def apply(args: String, prefix: String): Unit =
    run(ConfReader(args, prefix, "conf"), prefix)

  def run(fullConfig: FullConfig, prefix: String): Any = {

    val showStacktrace = fullConfig.args.contains("stack")

    def printMessageAndStacktrace(
        e: Throwable,
        showStacktrace: Boolean
    ): Unit = {

      error(e.getMessage)

      if (showStacktrace) {
        e.printStackTrace()
      }
    }

    FunctionRunner.run(fullConfig) match {
      case Success(res) => res
      case Failure(iae: IllegalArgException) =>
        printMessageAndStacktrace(iae, showStacktrace)
      case Failure(fnfe: FunctionNotFoundException) =>
        error("Function '" + fnfe.function.head + "' not found")
      case Failure(e) => printMessageAndStacktrace(e, showStacktrace)
    }

  }
}
