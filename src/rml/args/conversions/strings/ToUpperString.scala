package rml.args.conversions.strings

import rml.args.arg._

class ToUpperString {
  def mapToType(value: String): String = value.toUpperCase  
}

case class UpperString(val key: String) extends ToUpperString with SingleArg[String]

case class JUpperString(val key: String) extends ToUpperString with JoinArg[String]

case class UpperStrings(val key: String) extends ToUpperString with ListArg[String]

case class PUpperString(val pos: Int) extends ToUpperString with PositionalArg[String]
