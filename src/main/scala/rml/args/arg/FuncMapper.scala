package rml.args.arg

import rml.args.config.FullConfig
import scala.util.Try

trait FuncMapper[+T] {

  val args: List[Arg[_]]
  
  def apply(config: FullConfig): Try[T]
}

object Mapper {

  def apply[T](argsx: List[Arg[_]])(mapper: FullConfig => Try[T]): FuncMapper[T] = new FuncMapper[T]{
    
    override val args = argsx
  
    override def apply(config: FullConfig): Try[T] = mapper(config)
  }
  
  def apply[T](argsx: Arg[_]*)(mapper: FullConfig => Try[T]): FuncMapper[T] = apply(argsx.toList)(mapper)
  
  def apply[T](mapper: FullConfig => Try[T]): FuncMapper[T] = apply(List.empty)(mapper)
}