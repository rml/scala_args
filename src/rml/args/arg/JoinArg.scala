package rml.args.arg

/**
 * Joins all arguments to a String, before mapping them as a single argument
 */
trait JoinArg[T] extends ArgMapper[T] {

  override type R = T
  
  override def mapListToType(argList: List[String]): T = mapToType(argList.mkString(" "))
}

