package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToBigDecimal {
  def mapToType(value: String): BigDecimal = try {
    BigDecimal(value)
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is not a valid BigDecimal")
  }
}

case class ABigDecimal(val key: String) extends ToBigDecimal with SingleArg[BigDecimal]

case class BigDecimals(val key: String) extends ToBigDecimal with ListArg[BigDecimal]

case class BigDecimals0(val key: String) extends ToBigDecimal with List0Arg[BigDecimal]

case class PBigDecimal(val pos: Int) extends ToBigDecimal with PositionalArg[BigDecimal]
