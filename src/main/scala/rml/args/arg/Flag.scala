package rml.args.arg
import rml.args.exceptions.IllegalArgException
import rml.args.argdecorator.WithFlagAlias

/**
 * Optional boolean argument. If the key exists, it is true, if the key is absent, it is false.
 */
case class Flag(val key: String) extends Arg[Boolean] {

  override def getUnused(argList: List[String]) = argList

  override def getUsed(argList: List[String]) = List.empty

  override def noInformationMissing(argMap: Map[String, List[String]]) = true
  
  override def apply(argMap: Map[String, List[String]]): Boolean = argMap.get(key) match {
    
    case None => false
    case Some(argList) => if(argList.isEmpty) true else
      throw new IllegalArgException("The flag " + key + " should not have any values (found " + argList.mkString("'", "', '", "')"))
  }

  override def withAlias(aliases: String*): Arg[Boolean] = WithFlagAlias(this, aliases: _*)
}