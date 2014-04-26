package rml.args.arg
import rml.args.exceptions.IllegalArgException
import rml.args.argdecorator.WithFlagAlias
import rml.args.domain.FullConfig

/**
 * Optional boolean argument. If the key exists, it is true, if the key is absent, it is false.
 */
case class Flag(val key: String) extends InputArg[Boolean] {

  override def getUnused(argList: List[String]) = argList

  override def getUsed(argList: List[String]) = List.empty

  override def noInformationMissing(config: FullConfig) = true
  
  override def apply(config: FullConfig): Boolean = config.isArg(key) && mapListToType(config.arg(key))
  
  override def mapListToType(stringArgs: List[String]): Boolean = {

    if(stringArgs.isEmpty) true else
      throw new IllegalArgException("The flag " + key + " should not have any values (found " + stringArgs.mkString("'", "', '", "')"))
  }

  override def withAlias(aliases: String*): InputArg[Boolean] = WithFlagAlias(this, aliases: _*)
}