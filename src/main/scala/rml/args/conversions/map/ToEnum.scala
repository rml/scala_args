package rml.args.conversions.map

import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg
import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg
import rml.args.exceptions.IllegalArgException
import rml.args.arg.SetRestriction

trait ToEnum[E <: Enumeration] extends SetRestriction {
    
  val enum: E
  val key: String
  def mapToType(value: String): E#Value = try {
    enum.withName(value)
  } catch {
    case nsee: NoSuchElementException => throw new IllegalArgException(key, value, (0 until enum.maxId).toList.map(enum(_).toString))
  }
  
  def allowed = enum.values.map(_.toString)
  
  override def toString = enum.values.mkString("[", ", ", "]")
}

case class AnEnum[E <: Enumeration](key: String, enum: E) extends ToEnum[E] with SingleArg[E#Value]

case class Enums[E <: Enumeration](key: String, enum: E) extends ToEnum[E] with ListArg[E#Value]

case class Enums0[E <: Enumeration](key: String, enum: E) extends ToEnum[E] with List0Arg[E#Value]

case class PEnum[E <: Enumeration](pos: Int, enum: E) extends ToEnum[E] with PositionalArg[E#Value]