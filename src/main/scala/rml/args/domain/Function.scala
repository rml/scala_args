package rml.args.domain

import rml.args.arg.Arg
import rml.args.arg.DescriptionMethods
import rml.args.exceptions.IllegalArgException
import rml.args.reader.ConfReader
import rml.args.manager.FunctionOrigin
import rml.args.arg.InputArg
import rml.args.arg.InputArg
import rml.args.arg.DependentArg

trait Function[T] extends DependentArg[T] with DescriptionMethods[Function[T]]{
  
  val origin: FunctionOrigin
  
  val args = List[Arg[_]]()
  
  def inputArg: Map[String, InputArg[_]] = inputArgs.map(a => a.key -> a).toMap

  def inputArgOption(key: String): Option[InputArg[_]] = inputArg.get(key)

  def keys: Set[String] = inputArgs.map(_.key)
  
  def apply(config: FullConfig): T
  
}