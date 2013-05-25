package rml.args.arg

import scala.tools.nsc.io.File
import rml.args.argdecorator.WithAlias
import rml.args.argdecorator.WithDefault

/**
 * Trait that represents an argument of type T
 */
trait Arg[+T] extends ConvenienceMethods[T] with DescriptionMethods[Arg[T]] {

  /**
   * Name that identifies the argument
   */
  val key: String
  
  /**
   * Checks, whether the argument has all information needed to return a meaningful value
   */
  def noInformationMissing(argMap: Map[String, List[String]]): Boolean

  /**
   * returns the strings not used by this argument
   */
  def getUnused(argList: List[String]): List[String] = List.empty
  
  /**
   * returns the strings used by this argument
   */
  def getUsed(argList: List[String]): List[String] = argList
  
  /**
   * Takes a map that is entirely built of strings and returns the argument value of type T
   */
  def apply(argMap: Map[String, List[String]]): T
  
}

