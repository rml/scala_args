package rml.args.arg.decorator
import rml.args.arg.InputArg
import rml.args.arg.FuncArg

/**
 * Wrapper, that makes an argument optional
 */
object Opt {

  def apply[T](arg: InputArg[T]): InputArg[Option[T]] = arg.map(Option(_))
  
  def apply[T](arg: FuncArg[T]): FuncArg[Option[T]] = arg.map(Option(_))
}