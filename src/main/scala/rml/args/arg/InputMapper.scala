package rml.args.arg

import scala.util.Try

import rml.args.arg.restriction.NoRestriction
import rml.args.arg.restriction.Restriction
import rml.args.config.FullConfig

trait InputMapper[+T] {

  /**
   * Result type for a single list element
   */
  type R
  
  def restriction: Restriction = NoRestriction
  
  lazy val typeInfo: String = "??"
  
  /**
   * returns the strings not used by this argument
   */
  def getUnused(argList: List[String]): List[String] = Nil
  
  /**
   * returns the strings used by this argument
   */
  def getUsed(argList: List[String]): List[String] = argList

  def apply(config: FullConfig, key: String, aliases: List[String]): Try[T]
  
}