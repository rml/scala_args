package rml.args.arg.decorator
import rml.args.arg.{FuncArg, InputArg}

import scala.util.{Failure, Success}

/** Wrapper, that makes an argument optional
  */
object Opt {

  def apply[T](arg: InputArg[T]): InputArg[Option[T]] =
    arg.mapLowLevel("arg -> Opt(arg)") { (inputArg, config) =>
      inputArg(config) match {
        case Failure(f) => Success(None)
        case Success(s) => Success(Some(s))
      }
    }

  def apply[T](arg: FuncArg[T]): FuncArg[Option[T]] = arg.mapLowLevel {
    (funcArg, config) =>
      funcArg(config) match {
        case Failure(f) => Success(None)
        case Success(s) => Success(Some(s))
      }
  }
}
