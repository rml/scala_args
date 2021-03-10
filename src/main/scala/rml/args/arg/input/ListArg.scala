package rml.args.arg.input
import rml.args.arg.InputCmdMapper

/** Maps each argument of a list individually and retuimport rml.args.arg.input.ListArg0
  * rns a list
  */
trait ListArg[T] extends InputCmdMapper[List[T]] {

  override type R = T

  override val structureInfo: String = "List[%s]"

  override def mapListToType(argList: List[String]): List[T] =
    argList.map(mapToType)
}
