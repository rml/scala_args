package rml.args.conversions.strings

import rml.args.argmapper.JoinArg
import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg
import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg

class ToLowerString {
  def mapToType(value: String): String = value.toLowerCase  
}

case class LowerString(val key: String) extends ToLowerString with SingleArg[String]

case class JLowerString(val key: String) extends ToLowerString with JoinArg[String]

case class LowerStrings(val key: String) extends ToLowerString with ListArg[String]

case class LowerStrings0(val key: String) extends ToLowerString with List0Arg[String]

case class PLowerString(val pos: Int) extends ToLowerString with PositionalArg[String]

