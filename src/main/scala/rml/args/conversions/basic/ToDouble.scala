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

trait ToDouble extends NotRestricted {
  
  val baseType: String = "Double"
    
  def mapToType(value: String): Double = try {
    value.toDouble
  } catch {
    case nfe: NumberFormatException => 
      throw new IllegalArgException("Value '" + value + "' is not a valid Double")
  }
}


object ADouble { def apply(key: String) = InputArg(key, new SingleArg[Double] with ToDouble) }

object JDouble { def apply(key: String) = InputArg(key, new JoinArg[Double] with ToDouble { override val sep = ""} ) }

object Doubles { def apply(key: String) = InputArg(key, new ListArg[Double] with ToDouble) }

object Doubles0{ def apply(key: String) = InputArg(key, new ListArg0[Double] with ToDouble) }

object PDouble { def apply(pos: Int)    = InputArg("-", new ToDouble with PositionalArg[Double]{ val position = pos }) }

