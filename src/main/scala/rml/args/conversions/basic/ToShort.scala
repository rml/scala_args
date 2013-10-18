package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToShort {
  def mapToType(value: String): Short = try {
    value.toShort
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Short range [" + Short.MinValue + ", " + Short.MaxValue + "]")
  }
}

case class AShort(val key: String) extends ToShort with SingleArg[Short]

case class Shorts(val key: String) extends ToShort with ListArg[Short]

case class Shorts0(val key: String) extends ToShort with List0Arg[Short]

case class PShort(val pos: Int) extends ToShort with PositionalArg[Short]
