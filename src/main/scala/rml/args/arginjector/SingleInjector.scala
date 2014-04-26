package rml.args.arginjector

import rml.args.arg.DependentArg
import rml.args.reader.CommandlineArgReader
import scala.collection.immutable.TreeMap
import rml.args.exceptions.IllegalArgException
import rml.args.argmapper.ArgMapper
import rml.args.argdecorator.ArgDelegator
import rml.args.domain.FullConfig
import rml.args.arg.InputArg
import rml.args.arg.Arg

abstract class SingleInjector[T](val arg: DependentArg[T], val valueArg: InputArg[String], val key: Option[String] = None) extends Injector with DependentArg[T] {

  val args = List(arg, valueArg)
  
  override def inputArgs(): Set[InputArg[_]] = Set(valueArg)
  
  override def noInformationMissing(config: FullConfig) = valueArg.noInformationMissing(config)

  override def apply(config: FullConfig): T = 
    arg(inject(config, Map(key.getOrElse(valueArg.key) -> valueArg(config))))

}