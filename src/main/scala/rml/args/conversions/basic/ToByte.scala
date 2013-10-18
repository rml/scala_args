package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class ToByte {
  def mapToType(value: String): Byte = try {
    value.toByte
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Byte range [" + Byte.MinValue + ", " + Byte.MaxValue + "]")
  }

}

case class AByte(val key: String) extends ToByte with SingleArg[Byte]

case class Bytes(val key: String) extends ToByte with ListArg[Byte]

case class Bytes0(val key: String) extends ToByte with List0Arg[Byte]

case class PByte(val pos: Int) extends ToByte with PositionalArg[Byte]
