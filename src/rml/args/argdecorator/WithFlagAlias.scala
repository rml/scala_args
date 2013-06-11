package rml.args.argdecorator

import rml.args.arg.Arg

/**
 * This class allows to define aliases for arguments.
 * 
 * Aliases don't work well with environment variables and config files yet.
 */
case class WithFlagAlias(arg: Arg[Boolean], aliases: String*) extends ArgDelegator[Boolean] {

  override def noInformationMissing(argMap: Map[String, List[String]]) = true

  override def apply(argMap: Map[String, List[String]]): Boolean = if(arg(argMap)) true 
    else aliases.find(argMap.contains) match {
    case Some(alias) => true
    case None => false
  }
  
  override def withAlias(aliases: String*): Arg[Boolean] = WithFlagAlias(this, aliases: _*)

}
