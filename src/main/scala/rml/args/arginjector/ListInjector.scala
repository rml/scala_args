package rml.args.arginjector

import rml.args.arg.Arg
import rml.args.reader.CommandlineArgReader
import scala.collection.immutable.TreeMap
import rml.args.exceptions.IllegalArgException
import rml.args.argmapper.ArgMapper
import rml.args.argdecorator.ArgDelegator

abstract class ListInjector[T](arg: Arg[T], key: String) extends Arg[List[T]] with Injector {

  override val showdesc: String = arg.showdesc

  override def noInformationMissing(argMap: Map[String, List[String]]) = argMap.contains(key)

  override def apply(argMap: Map[String, List[String]]): List[T] = 
    argMap(key).map(k => arg(inject(argMap, Map(key -> k))) )
}