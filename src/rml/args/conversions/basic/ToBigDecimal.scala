package rml.args.conversions.basic

import rml.args.arg._

class ToBigDecimal {
  def mapToType(value: String): BigDecimal = BigDecimal(value)
}

case class ABigDecimal(val key: String) extends ToBigDecimal with SingleArg[BigDecimal]

case class BigDecimals(val key: String) extends ToBigDecimal with ListArg[BigDecimal]

case class PBigDecimal(val pos: Int) extends ToBigDecimal with PositionalArg[BigDecimal]
