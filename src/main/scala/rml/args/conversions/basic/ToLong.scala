package rml.args.conversions.basic

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.NotRestricted
import rml.args.exceptions.IllegalArgException

trait ToLong extends NotRestricted {

  val baseType: String = "Long"

  def mapToType(value: String): Long = try {
    value.toLong
  } catch {
    case nfe: NumberFormatException =>
      throw new IllegalArgException(
        "Value '" + value + "' is outside the valid Long range [" + Long.MinValue + ", " + Long.MaxValue + "]"
      )
  }
}

object ALong {
  def apply(key: String): InputArg[Long] =
    InputArg(key, new SingleArg[Long] with ToLong)
}

object JLong {
  def apply(key: String): InputArg[Long] =
    InputArg(key, new JoinArg[Long] with ToLong { override val sep = "" })
}

object Longs {
  def apply(key: String): InputArg[List[Long]] =
    InputArg(key, new ListArg[Long] with ToLong)
}

object Longs0 {
  def apply(key: String): InputArg[List[Long]] =
    InputArg(key, new ListArg0[Long] with ToLong)
}

object PLong {
  def apply(pos: Int): InputArg[Long] =
    InputArg(
      "-",
      new ToLong with PositionalArg[Long] { val position: Int = pos }
    )
}
