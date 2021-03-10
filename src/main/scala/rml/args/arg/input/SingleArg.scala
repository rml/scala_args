package rml.args.arg.input
import rml.args.arg.InputCmdMapper
import rml.args.exceptions.IllegalArgException

/** Argument lists must have exactly one element
  */
trait SingleArg[T] extends InputCmdMapper[T] {

  override type R = T

  override def getUnused(argList: List[String]): List[String] = argList.drop(1)

  override def getUsed(argList: List[String]): List[String] = argList.take(1)

  override def mapListToType(values: List[String]): T = values match {
    case first :: Nil => mapToType(first)
    case _ =>
      throw new IllegalArgException(
        "Expected exactly one parameter but found " + values.size + " parameters (" + values + ")"
      )
  }
}
