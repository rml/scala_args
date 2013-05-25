package rml.args.conversions.basic

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class BaseToBoolean(trueVals: Set[String], falseVals: Set[String] = Set()){
  
  def mapToType(value: String): Boolean = if(falseVals.isEmpty) trueVals.contains(value) 
		  else {
		    if(trueVals.contains(value)) true
		    else if(falseVals.contains(value)) false
		    else throw new IllegalArgException 
		  }
}

class ToBoolean extends BaseToBoolean(Set("1", "t", "T", "y", "Y", "true", "TRUE", "yes", "YES"))

case class ABoolean(val key: String) extends ToBoolean with SingleArg[Boolean]

case class Booleans(val key: String) extends ToBoolean with ListArg[Boolean]

case class Booleans0(val key: String) extends ToBoolean with List0Arg[Boolean]

case class PBoolean(val pos: Int) extends ToBoolean with PositionalArg[Boolean]
