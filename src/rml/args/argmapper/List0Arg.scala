package rml.args.argmapper

/**
 * Maps each argument of a list individually and returns a list
 * If the argument is missing, it is treated as a list of length 0
 */
trait List0Arg[T] extends ListArg[T] {

  override def noInformationMissing(argMap: Map[String, List[String]]) = true
  
  override def apply(argMap: Map[String, List[String]]): List[T] =
    if(argMap.contains(key)) super.apply(argMap) else List[T]()
}

