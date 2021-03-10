package rml.args.arg.input

import rml.args.arg.InputCmdMapper

/** Joins all arguments to a String, before mapping them as a single argument
  */
trait JoinArg[T] extends InputCmdMapper[T] {

  val sep: String = " "

  override type R = T

  override def mapListToType(argList: List[String]): T = mapToType(
    argList.mkString(sep)
  )
}
