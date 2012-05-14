package rml.args.arg

/**
 * This class allows to define aliases for arguments.
 * 
 * Aliases don't work well with environment variables and config files yet.
 */
case class WithAlias[T](arg: Arg[T], aliases: String*) extends Arg[T] {

  val key = arg.key

  override val desc: String = arg.desc
    
  def allKeysFound(argMap: Map[String, List[String]]) = argMap.contains(key) || aliases.exists(argMap.contains)

  def apply(argMap: Map[String, List[String]]): T = if(arg.allKeysFound(argMap)) arg(argMap) 
    else aliases.find(argMap.contains) match {
    case Some(alias) => arg(argMap + (key -> argMap(alias)))
    case None => throw new IllegalArgumentException("No value found for " + key + ", " + aliases.mkString(", "))
  }
}