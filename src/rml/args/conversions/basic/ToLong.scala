package rml.args.conversions.basic

import rml.args.arg._

class ToLong {
  def mapToType(value: String): Long = value.toLong
}

case class ALong(val key: String) extends ToLong with SingleArg[Long]

case class Longs(val key: String) extends ToLong with ListArg[Long]

case class PLong(val pos: Int) extends ToLong with PositionalArg[Long]
