package rml.args.conversions.strings

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.NotRestricted

trait ToUpperString extends NotRestricted {

  val baseType: String = "UpperString"

  def mapToType(value: String): String = value.toUpperCase
}

object AUpperString {
  def apply(key: String): InputArg[String] =
    InputArg(key, new SingleArg[String] with ToUpperString)
}

object JUpperString {
  def apply(key: String): InputArg[String] =
    InputArg(key, new JoinArg[String] with ToUpperString)
}

object UpperStrings {
  def apply(key: String): InputArg[List[String]] =
    InputArg(key, new ListArg[String] with ToUpperString)
}

object UpperStrings0 {
  def apply(key: String): InputArg[List[String]] =
    InputArg(key, new ListArg0[String] with ToUpperString)
}

object PUpperString {
  def apply(pos: Int): InputArg[String] = InputArg(
    "-",
    new ToUpperString with PositionalArg[String] { val position: Int = pos }
  )
}
