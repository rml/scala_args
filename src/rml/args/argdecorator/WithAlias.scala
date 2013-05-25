package rml.args.argdecorator

import rml.args.arg.Arg
import rml.args.exceptions.IllegalArgException

/**
 * This class allows to define aliases for arguments.
 * 
 * Aliases don't work well with environment variables and config files yet.
 */
case class WithAlias[+T](arg: Arg[T], aliases: String*) extends ArgDelegator[T] {

  override def showdesc: String = super.showdesc + aliases.mkString(" (alias ", ", ", ")")
    
  override def noInformationMissing(argMap: Map[String, List[String]]) = argMap.contains(key) || aliases.exists(argMap.contains)

  override def apply(argMap: Map[String, List[String]]): T = if(arg.noInformationMissing(argMap)) arg(argMap) 
    else aliases.find(argMap.contains) match {
    case Some(alias) => arg(argMap + (key -> argMap(alias)))
    case None => throw new IllegalArgException("No value found for " + key + ", " + aliases.mkString(", "))
  }
}