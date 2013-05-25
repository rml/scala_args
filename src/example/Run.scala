package example

import rml.args.manager.DefaultRunner
import rml.args.manager.DefaultSetup
import rml.args.manager.FunctionRegister
import rml.args.domain./
import rml.args.domain.Func
import rml.args.conversions.basic._
import rml.args.conversions.strings._
import rml.args.argdecorator._

object Run {

  RunBasic
  RunDiv
  RunFile
  RunString

  def main(args: Array[String]) {

    val prefix = "GG_"

    DefaultSetup(prefix)
    DefaultRunner(args, prefix)
  }

}