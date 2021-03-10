package rml.args.conversions.basic

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.NotRestricted
import rml.args.exceptions.IllegalArgException

trait ToInt extends NotRestricted {

  val baseType: String = "Int"

  def mapToType(value: String): Int = try {
    value.toInt
  } catch {
    case nfe: NumberFormatException =>
      throw new IllegalArgException(
        "Value '" + value + "' is outside the valid Int range [" + Int.MinValue + ", " + Int.MaxValue + "]"
      )
  }
}

object AInt {
  def apply(key: String): InputArg[Int] =
    InputArg(key, new SingleArg[Int] with ToInt)
}

object AnInt {
  def apply(key: String): InputArg[Int] =
    InputArg(key, new SingleArg[Int] with ToInt)
}

object JInt {
  def apply(key: String): InputArg[Int] =
    InputArg(key, new JoinArg[Int] with ToInt { override val sep = "" })
}

object Ints {
  def apply(key: String): InputArg[List[Int]] =
    InputArg(key, new ListArg[Int] with ToInt)
}

object Ints0 {
  def apply(key: String): InputArg[List[Int]] =
    InputArg(key, new ListArg0[Int] with ToInt)
}

object PInt {
  def apply(pos: Int): InputArg[Int] =
    InputArg("-", new ToInt with PositionalArg[Int] { val position: Int = pos })
}
