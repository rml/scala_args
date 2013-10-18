package rml.args.arg

/**
 * Argument with constant result value. Once it is defined, it never changes (unlike other arguments,
 * whose values may depend on command line input).
 * Fix arguments are good candidates for default values, as they are guaranteed to return a value.
 */
case class FixArg[T](val fixArg: T, val key: String = "[Fix Arg]") extends Arg[T] {

  override def showdesc: String = fixArg.toString()

  override def noInformationMissing(argMap: Map[String, List[String]]) = true

  override def apply(argMap: Map[String, List[String]]): T = fixArg
}