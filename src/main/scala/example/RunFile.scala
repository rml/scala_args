package example

import rml.args.arg.Func
import rml.args.arg.function.FunctionOrigin
import rml.args.conversions.files.CwdOrDir
import rml.args.conversions.files.HomeOrDir
import rml.args.conversions.files.PScalaFile
import rml.args.register.@@

object RunFile {

  implicit val origin: FunctionOrigin = FunctionOrigin("RunFile")

  @@("cat") -->
  Func(PScalaFile(1)){ f => f.lines().foreach(println) }
  
  @@("cod") -->
  Func(CwdOrDir("-")){ println }
  
  @@("hod") -->
  Func(HomeOrDir("-")){ println }
  
}