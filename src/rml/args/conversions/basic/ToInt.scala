package rml.args.conversions.basic

import rml.args.arg._

class ToInt {
  def mapToType(value: String): Int = value.toInt
}

case class AnInt(val key: String) extends ToInt with SingleArg[Int]

case class Ints(val key: String) extends ToInt with ListArg[Int]

case class PInt(val pos: Int) extends ToInt with PositionalArg[Int]
