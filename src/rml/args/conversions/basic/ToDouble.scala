package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToDouble {
  def mapToType(value: String): Double = try {
    value.toDouble
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is not a valid Double")
  }
}

case class ADouble(val key: String) extends ToDouble with SingleArg[Double]

case class Doubles(val key: String) extends ToDouble with ListArg[Double]

case class Doubles0(val key: String) extends ToDouble with List0Arg[Double]

case class PDouble(val pos: Int) extends ToDouble with PositionalArg[Double]
