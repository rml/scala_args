package rml.args.argdecorator

import rml.args.arg._
import rml.args.exceptions.IllegalArgException
import rml.args.domain.FullConfig
import rml.args.arg.FixArg

/**
 * Tries to get a value from the argument and all default arguments, until it finds one.
 * If no value is found, an exception is thrown.
 */
class WithDefault[+T](arg: DependentArg[T], defaultArgs: Arg[T]*) extends DependentArg[T] {
  
  private val allArgs = arg :: defaultArgs.toList
  
  val args = arg :: Nil
  
  override def noInformationMissing(config: FullConfig) = allArgs.exists(_.noInformationMissing(config))

  override def apply(config: FullConfig): T = allArgs.find(_.noInformationMissing(config)) match {
    case Some(matchingArg) => matchingArg.apply(config)
    case None => throw new IllegalArgException("No default value found for ")
  }
}

class WithInputDefault[+T](val arg: InputArg[T], defaultArgs: Arg[T]*) extends ArgDelegator[T] {
  
  private val allArgs = arg :: defaultArgs.toList
  
  override def showdesc: String = super.showdesc + defaultArgs.map(_.inputArgs.map(_.showdesc)).mkString(" (default ", ", ", ")")
    
  override def noInformationMissing(config: FullConfig) = allArgs.exists(_.noInformationMissing(config))

  override def apply(config: FullConfig): T = allArgs.find(_.noInformationMissing(config)) match {
    case Some(matchingArg) => matchingArg.apply(config)
    case None => throw new IllegalArgException("No default value found for " + key)
  }
}

object WithDefault {
  
  def apply[T](arg: DependentArg[T], defaultArgs: Arg[T]*): DependentArg[T] = new WithDefault(arg, defaultArgs: _*)
  
  def apply[T](arg: DependentArg[T], default: T): DependentArg[T] = apply(arg, FixArg(default))
  
  def apply[T](arg: InputArg[T], defaultArgs: Arg[T]*): InputArg[T] = new WithInputDefault(arg, defaultArgs: _*)
  
  def apply[T](arg: InputArg[T], default: T): InputArg[T] = apply(arg, FixArg(default))
  
}
