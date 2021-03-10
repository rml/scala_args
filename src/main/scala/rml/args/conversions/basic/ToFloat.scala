package rml.args.conversions.basic

import rml.args.arg._
import rml.args.arg.input._
import rml.args.arg.restriction.NotRestricted
import rml.args.exceptions.IllegalArgException

trait ToFloat extends NotRestricted {

  val baseType: String = "Float"

  def mapToType(value: String): Float = try {
    value.toFloat
  } catch {
    case nfe: NumberFormatException =>
      throw new IllegalArgException(
        "Value '" + value + "' is not a valid Float"
      )
  }
}

object AFloat {
  def apply(key: String): InputArg[Float] =
    InputArg(key, new SingleArg[Float] with ToFloat)
}

object JFloat {
  def apply(key: String): InputArg[Float] =
    InputArg(key, new JoinArg[Float] with ToFloat { override val sep = "" })
}

object Floats {
  def apply(key: String): InputArg[List[Float]] =
    InputArg(key, new ListArg[Float] with ToFloat)
}

object Floats0 {
  def apply(key: String): InputArg[List[Float]] =
    InputArg(key, new ListArg0[Float] with ToFloat)
}

object PFloat {
  def apply(pos: Int): InputArg[Float] =
    InputArg(
      "-",
      new ToFloat with PositionalArg[Float] { val position: Int = pos }
    )
}
