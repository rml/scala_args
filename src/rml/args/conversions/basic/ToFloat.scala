package rml.args.conversions.basic

import rml.args.arg._

class ToFloat {
  def mapToType(value: String): Float = value.toFloat
}

case class AFloat(val key: String) extends ToFloat with SingleArg[Float]

case class Floats(val key: String) extends ToFloat with ListArg[Float]

case class PFloat(val pos: Int) extends ToFloat with PositionalArg[Float]
