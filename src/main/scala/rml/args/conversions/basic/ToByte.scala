package rml.args.conversions.basic

import rml.args.arg._
import rml.args.arg.input._
import rml.args.exceptions.IllegalArgException
import rml.args.arg.restriction.NotRestricted
import rml.args.arg.input.SingleArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.ListArg
import rml.args.arg.input.JoinArg

trait ToByte extends NotRestricted {

  val baseType: String = "Byte"
  
  def mapToType(value: String): Byte = try {
    value.toByte
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Byte range [" + Byte.MinValue + ", " + Byte.MaxValue + "]")
  }

}


object AByte { def apply(key: String) = InputArg(key, new SingleArg[Byte] with ToByte) }

object JByte { def apply(key: String) = InputArg(key, new JoinArg[Byte] with ToByte { override val sep = ""} ) }

object Bytes { def apply(key: String) = InputArg(key, new ListArg[Byte] with ToByte) }

object Bytes0{ def apply(key: String) = InputArg(key, new ListArg0[Byte] with ToByte) }

object PByte { def apply(pos: Int)    = InputArg("-", new ToByte with PositionalArg[Byte]{ val position = pos }) }

