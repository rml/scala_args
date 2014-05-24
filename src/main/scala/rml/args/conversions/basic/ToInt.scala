package rml.args.conversions.basic

import rml.args.arg.InputArg
import rml.args.exceptions.IllegalArgException
import rml.args.arg.restriction.NotRestricted
import rml.args.arg.InputArg
import rml.args.exceptions.IllegalArgException
import rml.args.arg.restriction.NotRestricted
import rml.args.arg.input.ListArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.JoinArg
import rml.args.arg.InputArg
import rml.args.arg.InputMapper
import rml.args.arg.ArgState
import rml.args.arg.Arg


trait ToInt extends NotRestricted {
  
  val baseType: String = "Int"
    
  def mapToType(value: String): Int = try {
    value.toInt
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is outside the valid Int range [" + Int.MinValue + ", " + Int.MaxValue + "]")
  }
}


object AInt { def apply(key: String) = InputArg(key, new SingleArg[Int] with ToInt) }

object AnInt { def apply(key: String) = InputArg(key, new SingleArg[Int] with ToInt) }

object JInt { def apply(key: String) = InputArg(key, new JoinArg[Int] with ToInt { override val sep = ""} ) }

object Ints { def apply(key: String) = InputArg(key, new ListArg[Int] with ToInt) }

object Ints0{ def apply(key: String) = InputArg(key, new ListArg0[Int] with ToInt) }

object PInt { def apply(pos: Int)    = InputArg("-", new ToInt with PositionalArg[Int]{ val position = pos }) }

