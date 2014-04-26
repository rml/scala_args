package rml.args.arg

import rml.args.domain.FullConfig

/**
 * Argument with constant result value. Once it is defined, it never changes (unlike other arguments,
 * whose values may depend on command line input).
 * Fix arguments are good candidates for default values, as they are guaranteed to return a value.
 */
case class FixArg[T](val fixArg: T) extends DependentArg[T] {

  val args = this :: Nil
  
  override def noInformationMissing(config: FullConfig) = true

  override def apply(config: FullConfig): T = fixArg
}