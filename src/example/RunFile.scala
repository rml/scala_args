package example

import rml.args.argdecorator.WithDefault
import rml.args.conversions.files._
import rml.args.domain.Func
import rml.args.manager.FunctionRegister
import scala.reflect.io.File

object RunFile {

  FunctionRegister("cat")             = Func(PFile(1)){ f => f.lines.foreach(println) }
  
  FunctionRegister("cod")             = Func(CwdOrDir("-")){ println }
  FunctionRegister("hod")             = Func(HomeOrDir("-")){ println }
  
}