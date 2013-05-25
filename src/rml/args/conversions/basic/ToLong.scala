package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToLong {
  def mapToType(value: String): Long = try {
    value.toLong
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Long range [" + Long.MinValue + ", " + Long.MaxValue + "]")
  }
}

case class ALong(val key: String) extends ToLong with SingleArg[Long]

case class Longs(val key: String) extends ToLong with ListArg[Long]

case class Longs0(val key: String) extends ToLong with List0Arg[Long]

case class PLong(val pos: Int) extends ToLong with PositionalArg[Long]
