package rml.args.conversions.map

import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg
import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg

trait ToEnum[E <: Enumeration] {
    
  val enum: E
  val key: String
  def mapToType(value: String): E#Value = enum.withName(value)
  
  override def toString = enum.values.mkString("[", ", ", "]")
}

case class AnEnum[E <: Enumeration](key: String, enum: E) extends ToEnum[E] with SingleArg[E#Value]

case class Enums[E <: Enumeration](key: String, enum: E) extends ToEnum[E] with ListArg[E#Value]

case class Enums0[E <: Enumeration](key: String, enum: E) extends ToEnum[E] with List0Arg[E#Value]

case class PEnum[E <: Enumeration](pos: Int, enum: E) extends ToEnum[E] with PositionalArg[E#Value]