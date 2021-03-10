package rml.args.arg.special

import rml.args.arg.{FuncArg, Mapper}

import scala.util.Success

/** Argument with constant result value. Once it is defined, it never changes (unlike other arguments,
  * whose values may depend on command line input).
  * Fix arguments are good candidates for default values, as they are guaranteed to return a value.
  */
object FixArg {

  def apply[T](fixArg: T): FuncArg[T] = FuncArg(Mapper(x => Success(fixArg)))

}
