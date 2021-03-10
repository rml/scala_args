package rml.args.help

import rml.args.arg.FuncArg
import rml.args.arg.function.FunctionOrigin
import rml.args.register.@@

object DefaultHelpSetup {

  implicit val origin: FunctionOrigin = FunctionOrigin("DefaultHelpSetup")

  def apply(prefix: String, systemPrefix: String = "@"): FuncArg[Unit] = {

    @@("help", "Display this help function") --> HelpFunctions().help
  }

}
