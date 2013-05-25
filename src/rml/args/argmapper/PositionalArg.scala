package rml.args.argmapper
import rml.args.exceptions.IllegalArgException

/**
 * Positional arguments have no names, they are accessed by position.
 */
trait PositionalArg[T] extends ArgMapper[T] {

  override type R = T

  val pos: Int
  
  require(pos > 0, "Positions start from 1")

  /**
   * Positional arguments are usually placed at the end, after all other parameters.
   * They can be marked explicitly by preceding them with '--'. This means, that their name
   * is '-', as all parameters start with a single hyphen. (e.g., -file => file, -- => -)
   */
  val key = "-"

  override def mapListToType(argList: List[String]): T = {
    val argArray = argList.toArray
    val idx = pos - 1
    if(idx < argArray.length) mapToType(argArray(idx)) else
      throw new IllegalArgException("No value at position " + pos + ". Found only " + argArray.length + " values (" + argList.mkString("'", "', '", "'") + ")")
  }
}
