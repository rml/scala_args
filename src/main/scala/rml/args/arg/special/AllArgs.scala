package rml.args.arg.special

import rml.args.arg.Mapper
import rml.args.arg.FuncArg
import scala.util.Success
import rml.args.config.FullConfig
import rml.args.arg.Arg

object AllArgs {
  
  def apply(): Arg[FullConfig] = FuncArg(Mapper(fullConfig => Success(fullConfig)))
}
