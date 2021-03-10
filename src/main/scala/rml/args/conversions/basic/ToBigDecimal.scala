package rml.args.conversions.basic

import rml.args.arg._
import rml.args.arg.input._
import rml.args.arg.restriction.NotRestricted
import rml.args.exceptions.IllegalArgException

trait ToBigDecimal extends NotRestricted {

  val baseType: String = "BigDecimal"

  def mapToType(value: String): BigDecimal = try {
    BigDecimal(value)
  } catch {
    case nfe: NumberFormatException =>
      throw new IllegalArgException(
        "Value '" + value + "' is not a valid BigDecimal"
      )
  }
}

object ABigDecimal {
  def apply(key: String): InputArg[BigDecimal] =
    InputArg(key, new SingleArg[BigDecimal] with ToBigDecimal)
}

object JBigDecimal {
  def apply(key: String): InputArg[BigDecimal] = InputArg(
    key,
    new JoinArg[BigDecimal] with ToBigDecimal { override val sep = "" }
  )
}

object BigDecimals {
  def apply(key: String): InputArg[List[BigDecimal]] =
    InputArg(key, new ListArg[BigDecimal] with ToBigDecimal)
}

object BigDecimals0 {
  def apply(key: String): InputArg[List[BigDecimal]] =
    InputArg(key, new ListArg0[BigDecimal] with ToBigDecimal)
}

object PBigDecimal {
  def apply(pos: Int): InputArg[BigDecimal] = InputArg(
    "-",
    new ToBigDecimal with PositionalArg[BigDecimal] { val position: Int = pos }
  )
}
