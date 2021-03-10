package rml.args.arg.special

import rml.args.arg.{Arg, FuncArg, Mapper}
import rml.args.config.FullConfig

import scala.util.Success

object AllArgs {

  def apply(): Arg[FullConfig] = FuncArg(
    Mapper(fullConfig => Success(fullConfig))
  )
}
