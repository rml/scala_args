package rml.args.argdecorator

import rml.args.arg.Arg

/**
 * Wrapper, that makes an argument optional
 */
case class Opt[O](arg: Arg[O]) extends Arg[Option[O]] {

  override def desc(text: String): Arg[Option[O]] = {
    arg.desc(text)
    this
  }
  
  override def --(text: String) = {
    desc(text)
  }
  
  override def showdesc = {
	arg.showdesc
  }

  override val key = arg.key

  override def noInformationMissing(argMap: Map[String, List[String]]) = true

  override def apply(argMap: Map[String, List[String]]): Option[O] = 
    if(arg.noInformationMissing(argMap)) Some(arg.apply(argMap)) else None
}