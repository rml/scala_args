package rml.args.arg

import rml.args.domain.FullConfig

case class AllArgs() extends DependentArg[FullConfig] {
  
  val args = Nil

  override def noInformationMissing(config: FullConfig) = true

  override def apply(config: FullConfig) = config

}