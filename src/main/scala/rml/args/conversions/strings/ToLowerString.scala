package rml.args.conversions.strings

import rml.args.arg.InputArg
import rml.args.arg.input.ListArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.JoinArg
import rml.args.arg.restriction.NotRestricted


trait ToLowerString extends NotRestricted {
  
  val baseType: String = "LowerString"
    
  def mapToType(value: String): String = value.toLowerCase  
}


object ALowerString { def apply(key: String) = InputArg(key, new SingleArg[String] with ToLowerString) }

object JLowerString { def apply(key: String) = InputArg(key, new JoinArg[String] with ToLowerString { override val sep = ""} ) }

object LowerStrings { def apply(key: String) = InputArg(key, new ListArg[String] with ToLowerString) }

object LowerStrings0{ def apply(key: String) = InputArg(key, new ListArg0[String] with ToLowerString) }

object PLowerString { def apply(pos: Int)    = InputArg("-", new ToLowerString with PositionalArg[String]{ val position = pos }) }

