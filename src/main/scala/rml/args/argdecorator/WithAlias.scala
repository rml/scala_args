package rml.args.argdecorator

import rml.args.arg.InputArg
import rml.args.domain.FullConfig
import rml.args.arg.DependentArg

case class WithAlias[+T](arg: InputArg[T], aliases: String*) extends ArgDelegator[T] {

  private def argValues(config: FullConfig) = config.argWithAlias(arg.key, aliases: _*)
  
  override def showdesc: String = super.showdesc + aliases.mkString(" (alias ", ", ", ")")
    
  override def noInformationMissing(config: FullConfig) = config.argWithAliasExists(key, aliases: _*)

  override def apply(config: FullConfig): T = arg.mapListToType(config.argWithAlias(key, aliases: _*))
}
