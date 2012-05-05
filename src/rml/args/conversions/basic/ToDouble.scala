package rml.args.conversions.basic

import rml.args.arg._

class ToDouble {
  def mapToType(value: String): Double = value.toDouble
}

case class ADouble(val key: String) extends ToDouble with SingleArg[Double]

case class Doubles(val key: String) extends ToDouble with ListArg[Double]

case class PDouble(val pos: Int) extends ToDouble with PositionalArg[Double]
