package rml.args.conversions.map

import rml.args.arg.input.ListArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.exceptions.IllegalArgException
import rml.args.arg.restriction.SetRestriction
import rml.args.arg.InputArg
import rml.args.arg.input.JoinArg
import rml.args.arg.input.ListArg0
import rml.args.arg.restriction.FixRestriction
import rml.args.arg.restriction.Restricted
import rml.args.arg.InputCmdMapper

trait ToEnum[E <: Enumeration] {
    
  val enum: E

  def mapToType(value: String): E#Value = try {
    enum.withName(value)
  } catch {
    case nsee: NoSuchElementException => throw new IllegalArgException("", value, (0 until enum.maxId).toList.map(enum(_).toString))
  }
  
  lazy val getRestriction = FixRestriction(enum.values.map(_.toString))
  
  lazy val baseType: String = "Enumeration " + enum.getClass().getName()
  
  override def toString = enum.values.mkString("[", ", ", "]")
}


object AnEnum{ def apply[E <: Enumeration](key: String, e: E) = InputArg(key, new SingleArg[E#Value] with ToEnum[E] { val enum = e } ) }

object JEnum { def apply[E <: Enumeration](key: String, e: E) = InputArg(key, new JoinArg[E#Value] with ToEnum[E] { val enum = e } ) }
  
object Enums { def apply[E <: Enumeration](key: String, e: E) = InputArg(key, new ListArg[E#Value] with ToEnum[E] { val enum = e } ) }
  
object Enums0{ def apply[E <: Enumeration](key: String, e: E) = InputArg(key, new ListArg0[E#Value] with ToEnum[E] { val enum = e } ) }
  
object PEnum { def apply[E <: Enumeration](pos: Int, e: E)    = InputArg("-", new ToEnum[E] with PositionalArg[E#Value]{ val enum = e ; val position = pos }) }
