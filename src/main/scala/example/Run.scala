package example

import rml.args.run.{DefaultRunner, DefaultSetup}

object Run {

  RunBasic
  RunDiv
  RunFile
  RunGenerate
  RunMap
  RunString

  def main(args: Array[String]): Unit = {

    val prefix = "GG_"

    DefaultSetup(prefix)
    DefaultRunner(args, prefix)
  }

}
