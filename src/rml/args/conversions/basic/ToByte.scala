package rml.args.conversions.basic

import rml.args.arg._

class ToByte {
  def mapToType(value: String): Byte = value.toByte
}

case class AByte(val key: String) extends ToByte with SingleArg[Byte]

case class Bytes(val key: String) extends ToByte with ListArg[Byte]

case class PByte(val pos: Int) extends ToByte with PositionalArg[Byte]
