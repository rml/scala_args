package rml.args.argdecorator

import scala.collection.immutable.Map
import rml.args.arg.Arg
import rml.args.arg.DescriptionMethods

trait ArgDelegator[+T] extends Arg[T] with DescriptionMethods[ArgDelegator[T]] {

  val arg: Arg[T]
  			
  override val key: String = arg.key
  
  override def noInformationMissing(argMap: Map[String, List[String]]): Boolean = arg.noInformationMissing(argMap)

  override def getUnused(argList: List[String]): List[String] = arg.getUnused(argList)
  
  override def getUsed(argList: List[String]): List[String] = arg.getUsed(argList)
  
  override def apply(argMap: Map[String, List[String]]): T = arg.apply(argMap)
  
}