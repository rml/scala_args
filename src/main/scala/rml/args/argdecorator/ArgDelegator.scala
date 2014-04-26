package rml.args.argdecorator

import rml.args.arg.DescriptionMethods
import rml.args.arg.InputArg
import rml.args.domain.FullConfig

trait ArgDelegator[+T] extends InputArg[T] with DescriptionMethods[ArgDelegator[T]] {

  val arg: InputArg[T]
  			
  override val key: String = arg.key
  
  override def noInformationMissing(config: FullConfig): Boolean = arg.noInformationMissing(config)

  override def getUnused(argList: List[String]): List[String] = arg.getUnused(argList)
  
  override def getUsed(argList: List[String]): List[String] = arg.getUsed(argList)
  
  override def apply(config: FullConfig): T = arg.apply(config)

  override def mapListToType(stringArgs: List[String]): T = arg.mapListToType(stringArgs)
}