package rml.args.conversions.strings

import rml.args.argmapper.JoinArg
import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg
import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg

class ToString{
  def mapToType(value: String): String = value
}

case class AString(val key: String) extends ToString with SingleArg[String]

case class JString(val key: String) extends ToString with JoinArg[String]

case class Strings(val key: String) extends ToString with ListArg[String]

case class Strings0(val key: String) extends ToString with List0Arg[String]

case class PString(val pos: Int) extends ToString with PositionalArg[String]
