package rml.args.arg

import rml.args.config.FullConfig
import scala.util.Try
import scala.reflect.runtime.universe._

/**
 * Trait that represents an argument of type T
 */
trait Arg[+T] {

  val typeInfo: String

  def inputArgs: Set[InputArg[_]]
  
  def apply(config: FullConfig): Try[T]
  
  def map[S](func: T => S): Arg[S]
  
  def flatMap[S](func: T => Try[S]): Arg[S]
}

