package example

import rml.args.run.DefaultRunner
import rml.args.run.DefaultSetup
import rml.args.logging.LoggerManager
import rml.args.logging.LogLevel._

object Run {

  RunBasic
  RunDiv
  RunFile
  RunGenerate
  RunMap
  RunString

  def main(args: Array[String]) {

    val prefix = "GG_"

    DefaultSetup(prefix)
    DefaultRunner(args, prefix)
  }

}