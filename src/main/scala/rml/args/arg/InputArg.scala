package rml.args.arg

import rml.args.domain.FullConfig

trait InputArg[+T] extends Arg[T] with InputConvenienceMethods[T] with DescriptionMethods[InputArg[T]] {

  val inputArgs: Set[InputArg[_]] = Set(this)
  
  /**
   * Name that identifies the argument
   */
  val key: String
  
  /**
   * returns the strings not used by this argument
   */
  def getUnused(argList: List[String]): List[String] = List.empty
  
  /**
   * returns the strings used by this argument
   */
  def getUsed(argList: List[String]): List[String] = argList

  def apply(config: FullConfig): T = mapListToType(config.arg(key))
  
  /**
   * 
   */
  def mapListToType(stringArgs: List[String]): T
  
}