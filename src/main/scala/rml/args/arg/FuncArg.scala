package rml.args.arg

import rml.args.config.FullConfig
import rml.args.arg.function.FunctionOrigin
import com.typesafe.scalalogging.slf4j.LazyLogging
import rml.args.arg.special.FixArg
import rml.args.exceptions.IllegalArgException
import scala.util.Try

final case class FuncArg[+T](mapper: FuncMapper[T], argState: ArgState = ArgState(), defaultArgs: List[Arg[T]] = List.empty)(implicit orig: FunctionOrigin) extends Arg[T] {

  val origin: FunctionOrigin = orig
  
  val description: String = argState.description

  override val typeInfo: String = "?"
  
  val args: List[Arg[_]] = mapper.args
  
  def inputArgs: Set[InputArg[_]] = args.flatMap(_.inputArgs).toSet
  
  override def apply(config: FullConfig): Try[T] = mapper(config)

  
  override def map[S](func: T => S): FuncArg[S] = {
    new FuncArg(Mapper{ config => this.apply(config).map(func)})
  }
  
  override def flatMap[S](func: T => Try[S]): FuncArg[S] = {
    new FuncArg(Mapper{ config => this.apply(config).flatMap(func)})
  }
  

  def inputArg: Map[String, InputArg[_]] = inputArgs.map(a => a.key -> a).toMap

  def inputArgOption(key: String): Option[InputArg[_]] = inputArg.get(key)

  def keys: Set[String] = inputArgs.map(_.key)

  def withDefault[X >: T](defaultArgs: Arg[X]*): FuncArg[X] = copy(defaultArgs = defaultArgs.toList)
  def         -> [X >: T](defaultArgs: Arg[X]*): FuncArg[X] = withDefault(defaultArgs: _*)
  
  def withDefault[X >: T](default: X): FuncArg[X] = withDefault(FixArg(default))
  def         -> [X >: T](default: X): FuncArg[X] = withDefault(default)
  
  def withDescription(desc: String): FuncArg[T] = copy(argState = argState.copy(description = desc))
  def             -- (desc: String): FuncArg[T] = withDescription(desc)
  
}


