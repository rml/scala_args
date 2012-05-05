package rml.args.arg

import scala.tools.nsc.io.File

/**
 * Trait that represents an argument of type T
 */
trait Arg[T] {

  /**
   * Name that identifies the argument
   */
  val key: String

  /**
   * Argument description (optional)
   */
  val desc: String = ""

  /**
   * Checks, whether the key (for simple arguments) or all keys (for complex arguments)
   * are present in the argument map
   */
  def allKeysFound(argMap: Map[String, List[String]]): Boolean

  /**
   * Takes a map that is entirely built of strings and returns the argument value of type T
   */
  def apply(argMap: Map[String, List[String]]): T
}

