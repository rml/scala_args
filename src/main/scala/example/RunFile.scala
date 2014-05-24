package example

import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.arg.Func
import rml.args.conversions.files.PFile
import rml.args.conversions.files.CwdOrDir
import rml.args.conversions.files.HomeOrDir

object RunFile {

  implicit val origin = FunctionOrigin("RunFile")

  FunctionRegister("cat")             = Func(PFile(1)){ f => f.lines.foreach(println) }
  
  FunctionRegister("cod")             = Func(CwdOrDir("-")){ println }
  FunctionRegister("hod")             = Func(HomeOrDir("-")){ println }
  
}