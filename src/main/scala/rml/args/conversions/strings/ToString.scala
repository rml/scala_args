package rml.args.conversions.strings

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.NotRestricted

trait ToString extends NotRestricted {

  val baseType: String = "String"

  def mapToType(value: String): String = value
}

object AString {
  def apply(key: String): InputArg[String] =
    InputArg(key, new SingleArg[String] with ToString)
}

object JString {
  def apply(key: String): InputArg[String] =
    InputArg(key, new JoinArg[String] with ToString)
}

object Strings {
  def apply(key: String): InputArg[List[String]] =
    InputArg(key, new ListArg[String] with ToString)
}

object Strings0 {
  def apply(key: String): InputArg[List[String]] =
    InputArg(key, new ListArg0[String] with ToString)
}

object PString {
  def apply(pos: Int): InputArg[String] = InputArg(
    "-",
    new ToString with PositionalArg[String] { val position: Int = pos }
  )
}
