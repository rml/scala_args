package rml.args.arg

/**
 * Tries to get a value from the argument and all default arguments, until it finds one.
 * If no value is found, an exception is thrown.
 */
case class WithDefault[T](arg: Arg[T], defaultArgs: Arg[T]*) extends Arg[T] {

  private val allArgs = arg :: defaultArgs.toList
  
  val key = arg.key

  override val desc: String = arg.desc
    
  def allKeysFound(argMap: Map[String, List[String]]) = allArgs.exists(_.allKeysFound(argMap))

  def apply(argMap: Map[String, List[String]]): T = allArgs.find(_.allKeysFound(argMap)) match {
    case Some(matchingArg) => matchingArg.apply(argMap)
    case None => throw new IllegalArgumentException("No default value found for " + key)
  }
}