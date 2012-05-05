package rml.args.conversions.strings

import rml.args.arg._

class ToLowerString {
  def mapToType(value: String): String = value.toLowerCase  
}

case class LowerString(val key: String) extends ToLowerString with SingleArg[String]

case class JLowerString(val key: String) extends ToLowerString with JoinArg[String]

case class LowerStrings(val key: String) extends ToLowerString with ListArg[String]

case class PLowerString(val pos: Int) extends ToLowerString with PositionalArg[String]

