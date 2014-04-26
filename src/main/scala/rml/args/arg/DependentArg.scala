package rml.args.arg

import rml.args.domain.FullConfig

trait DependentArg[+T] extends Arg[T] with DependantConvenienceMethods[T] with DescriptionMethods[DependentArg[T]] {

  val args: List[Arg[_]]
  
  def inputArgs(): Set[InputArg[_]] = args.flatMap(_.inputArgs).toSet
  
  def noInformationMissing(config: FullConfig): Boolean = args.forall(_.noInformationMissing(config))

}