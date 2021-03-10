package rml.args.arg.input
import rml.args.arg.InputCmdMapper
import rml.args.exceptions.IllegalArgException

/** Positional arguments have no names, they are accessed by position.
  */
trait PositionalArg[T] extends InputCmdMapper[T] {

  override type R = T

  val position: Int

  /** Positional arguments are usually placed at the end, after all other parameters.
    * They can be marked explicitly by preceding them with '--'. This means, that their name
    * is '-', as all parameters start with a single hyphen. (e.g., -file => file, -- => -)
    */
  val key = "-"

  override def mapListToType(argList: List[String]): T = {
    val argArray = argList.toArray
    val idx = position - 1
    if (idx < argArray.length) mapToType(argArray(idx))
    else
      throw new IllegalArgException(
        "No value at position " + position + ". Found only " + argArray.length + " values (" + argList
          .mkString("'", "', '", "'") + ")"
      )
  }
}
