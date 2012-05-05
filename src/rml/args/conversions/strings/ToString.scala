package rml.args.conversions.strings

import rml.args.arg._

class ToString{
  def mapToType(value: String): String = value
}

case class AString(val key: String) extends ToString with SingleArg[String]

case class JString(val key: String) extends ToString with JoinArg[String]

case class Strings(val key: String) extends ToString with ListArg[String]

case class PString(val pos: Int) extends ToString with PositionalArg[String]
