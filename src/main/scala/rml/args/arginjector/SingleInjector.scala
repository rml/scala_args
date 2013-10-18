package rml.args.arginjector

import rml.args.arg.Arg
import rml.args.reader.CommandlineArgReader
import scala.collection.immutable.TreeMap
import rml.args.exceptions.IllegalArgException
import rml.args.argmapper.ArgMapper
import rml.args.argdecorator.ArgDelegator

abstract class SingleInjector[T](val arg: Arg[T], override val key: String) extends ArgDelegator[T] with Injector {

  override def getUnused(argList: List[String]) = argList.drop(1)

  override def getUsed(argList: List[String]) = argList.take(1)
  
  override def noInformationMissing(argMap: Map[String, List[String]]) = argMap.contains(key)

  override def apply(argMap: Map[String, List[String]]): T = arg(inject(argMap, Map(key -> argMap(key).head)))
}