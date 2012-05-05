package rml.args.arg

/**
 * Argument lists must have exactly one element
 */
trait SingleArg[T] extends ArgMapper[T] {

  override type R = T

  override def getUnused(argList: List[String]) = argList.drop(1)

  override def getUsed(argList: List[String]) = argList.take(1)

  override def mapListToType(values: List[String]): T = values match {
    case first :: Nil => mapToType(first)
    case _ => throw new IllegalArgumentException("Expected exactly one parameter but found " + values.size + " parameters (" + values + ")")
  }  
}
