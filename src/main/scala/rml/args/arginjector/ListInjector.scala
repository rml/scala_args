package rml.args.arginjector

import rml.args.arg.Arg
import rml.args.reader.CommandlineArgReader
import scala.collection.immutable.TreeMap
import rml.args.exceptions.IllegalArgException
import rml.args.argmapper.ArgMapper
import rml.args.argdecorator.ArgDelegator
import rml.args.domain.FullConfig
import rml.args.arg.DependentArg
import rml.args.arg.InputArg

abstract class ListInjector[T](arg: DependentArg[T], val valueArg: InputArg[List[String]], val key: Option[String] = None) extends Arg[List[T]] with Injector with DependentArg[List[T]] {

  val args = List(arg, valueArg)
  
  override def inputArgs(): Set[InputArg[_]] = Set(valueArg)
  
  override def noInformationMissing(config: FullConfig) = valueArg.noInformationMissing(config)

  override def apply(config: FullConfig): List[T] = 
    valueArg(config).map(k => arg(inject(config, Map(key.getOrElse(valueArg.key) -> k))) )
  
}