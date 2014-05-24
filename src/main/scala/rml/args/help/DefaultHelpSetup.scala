package rml.args.help

import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.register.@@

object DefaultHelpSetup {

  implicit val origin = FunctionOrigin("DefaultHelpSetup")
  
  def apply(prefix: String, systemPrefix: String = "@") = {

    @@("help", "Display this help function") --> HelpFunctions().help
  }

}