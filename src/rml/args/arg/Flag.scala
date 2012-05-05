package rml.args.arg

/**
 * Optional boolean argument. If the key exists, it is true, if the key is absent, it is false.
 */
case class Flag(val key: String) extends Arg[Boolean] {

  override def allKeysFound(argMap: Map[String, List[String]]) = true
  
  override def apply(argMap: Map[String, List[String]]): Boolean = argMap.get(key) match {
    
    case None => false
    case Some(argList) => if(argList.isEmpty) true else
      throw new IllegalArgumentException("The flag " + key + " should not have any values (found " + argList.mkString("'", "', '", "')"))
  }

}