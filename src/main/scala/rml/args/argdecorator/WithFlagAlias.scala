package rml.args.argdecorator

import rml.args.arg.Arg
import rml.args.domain.FullConfig
import rml.args.arg.InputArg

/**
 * This class allows to define aliases for arguments.
 * 
 * Aliases don't work well with environment variables and config files yet.
 */
case class WithFlagAlias(arg: InputArg[Boolean], aliases: String*) extends ArgDelegator[Boolean] {

  override def noInformationMissing(config: FullConfig) = true

  override def apply(config: FullConfig): Boolean = config.argWithAliasExists(arg.key, aliases: _*)
  
  override def withAlias(aliases: String*): InputArg[Boolean] = WithFlagAlias(this, aliases: _*)

}
