package rml.args.argdecorator

import rml.args.arg._
import rml.args.exceptions.IllegalArgException

/**
 * Tries to get a value from the argument and all default arguments, until it finds one.
 * If no value is found, an exception is thrown.
 */
case class WithDefault[+T](arg: Arg[T], defaultArgs: Arg[T]*) extends ArgDelegator[T] {
  
  def this(arg: Arg[T], default: T) = this(arg, FixArg(default))
  
  private val allArgs = arg :: defaultArgs.toList
  
  override def showdesc: String = super.showdesc + defaultArgs.map(_.showdesc).mkString(" (default ", ", ", ")")
    
  override def noInformationMissing(argMap: Map[String, List[String]]) = allArgs.exists(_.noInformationMissing(argMap))

  override def apply(argMap: Map[String, List[String]]): T = allArgs.find(_.noInformationMissing(argMap)) match {
    case Some(matchingArg) => matchingArg.apply(argMap)
    case None => throw new IllegalArgException("No default value found for " + key)
  }
}