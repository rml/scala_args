package rml.args.arg

/**
 * Maps each argument of a list individually and returns a list
 */
trait ListArg[T] extends ArgMapper[List[T]] {

  override type R = T

  override def mapListToType(argList: List[String]): List[T] = argList.map(mapToType)
}

