package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToFloat {
  def mapToType(value: String): Float = try {
    value.toFloat
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is not a valid Float")
  }
}

case class AFloat(val key: String) extends ToFloat with SingleArg[Float]

case class Floats(val key: String) extends ToFloat with ListArg[Float]

case class Floats0(val key: String) extends ToFloat with List0Arg[Float]

case class PFloat(val pos: Int) extends ToFloat with PositionalArg[Float]
