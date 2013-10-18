package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToInt {
  def mapToType(value: String): Int = try {
    value.toInt
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Int range [" + Int.MinValue + ", " + Int.MaxValue + "]")
  }
}

case class AnInt(val key: String) extends ToInt with SingleArg[Int]

case class Ints(val key: String) extends ToInt with ListArg[Int]

case class Ints0(val key: String) extends ToInt with List0Arg[Int]

case class PInt(val pos: Int) extends ToInt with PositionalArg[Int]
