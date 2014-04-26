package rml.args.argdecorator

import rml.args.arg.Arg
import rml.args.domain.FullConfig
import rml.args.arg.InputArg
import rml.args.arg.DependentArg

/**
 * Wrapper, that makes an argument optional
 */
class Opt[O](arg: Arg[O]) extends DependentArg[Option[O]] {

  val args = arg :: Nil
  
  override def noInformationMissing(config: FullConfig) = true

  override def apply(config: FullConfig): Option[O] = 
    if(arg.noInformationMissing(config)) Some(arg.apply(config)) else None
}

class InputOpt[O](arg: InputArg[O]) extends InputArg[Option[O]] {

  override def desc(text: String): InputArg[Option[O]] = {
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

  override def noInformationMissing(config: FullConfig) = true

  override def apply(config: FullConfig): Option[O] = 
    if(arg.noInformationMissing(config)) Some(arg.apply(config)) else None
    
  def mapListToType(stringArgs: List[String]): Option[O] = Some(arg.mapListToType(stringArgs))    
}

object Opt {
  
  def apply[O](arg: Arg[O]): Arg[Option[O]] = new Opt(arg)
  
  def apply[O](arg: InputArg[O]): InputArg[Option[O]] = new InputOpt(arg)
  
}
