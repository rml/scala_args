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

trait BaseToBoolean extends NotRestricted {

  val trueVals: Set[String]
  val falseVals: Set[String] = Set()

  val baseType: String = "Boolean"
  
  def mapToType(value: String): Boolean = if(falseVals.isEmpty) trueVals.contains(value) 
		  else {
		    if(trueVals.contains(value)) true
		    else if(falseVals.contains(value)) false
		    else throw new IllegalArgException 
		  }
}

trait ToBoolean extends BaseToBoolean {
  override val trueVals: Set[String] = (Set("1", "t", "T", "y", "Y", "true", "TRUE", "yes", "YES"))
}


object ABoolean { def apply(key: String) = InputArg(key, new SingleArg[Boolean] with ToBoolean) }

object JBoolean { def apply(key: String) = InputArg(key, new JoinArg[Boolean] with ToBoolean { override val sep = ""} ) }

object Booleans { def apply(key: String) = InputArg(key, new ListArg[Boolean] with ToBoolean) }

object Booleans0{ def apply(key: String) = InputArg(key, new ListArg0[Boolean] with ToBoolean) }

object PBoolean { def apply(pos: Int)    = InputArg("-", new ToBoolean with PositionalArg[Boolean]{ val position = pos }) }

