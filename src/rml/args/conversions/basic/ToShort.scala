package rml.args.conversions.basic

import rml.args.arg._

class ToShort {
  def mapToType(value: String): Short = value.toByte
}

case class AShort(val key: String) extends ToShort with SingleArg[Short]

case class Shorts(val key: String) extends ToShort with ListArg[Short]

case class PShort(val pos: Int) extends ToShort with PositionalArg[Short]
