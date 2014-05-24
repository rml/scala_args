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

trait ToShort extends NotRestricted {
  
  val baseType: String = "Short"
    
  def mapToType(value: String): Short = try {
    value.toShort
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Short range [" + Short.MinValue + ", " + Short.MaxValue + "]")
  }
}


object AShort { def apply(key: String) = InputArg(key, new SingleArg[Short] with ToShort) }

object JShort { def apply(key: String) = InputArg(key, new JoinArg[Short] with ToShort { override val sep = ""} ) }

object Shorts { def apply(key: String) = InputArg(key, new ListArg[Short] with ToShort) }

object Shorts0{ def apply(key: String) = InputArg(key, new ListArg0[Short] with ToShort) }

object PShort { def apply(pos: Int)    = InputArg("-", new ToShort with PositionalArg[Short]{ val position = pos }) }

