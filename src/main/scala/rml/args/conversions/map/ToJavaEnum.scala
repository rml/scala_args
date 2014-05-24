package rml.args.conversions.map

import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.arg.input.ListArg
import rml.args.arg.restriction.SetRestriction
import rml.args.arg.InputArg
import rml.args.arg.input.JoinArg
import rml.args.arg.input.ListArg0
import rml.args.arg.restriction.FixRestriction
import rml.args.arg.restriction.Restricted

trait ToJavaEnum[C <: Enum[C]] {

  val clazz: java.lang.Class[C]
  def mapToType(value: String) = Enum.valueOf(clazz, value)
  
  lazy val baseType: String = clazz.toString().replace("class", "Enum")
  
  lazy val getRestriction = FixRestriction(clazz.getEnumConstants().map(_.toString).toSet)
  
  override def toString = clazz.getEnumConstants().mkString(clazz.getName() + "[", ", ", "]")
}


object AJavaEnum { def apply[C <: Enum[C]](key: String, clzz: java.lang.Class[C]) = InputArg(key, new SingleArg[C] with ToJavaEnum[C] { val clazz = clzz } ) }
  
object JJavaEnum { def apply[C <: Enum[C]](key: String, clzz: java.lang.Class[C]) = InputArg(key, new JoinArg[C] with ToJavaEnum[C] { val clazz = clzz } ) }
  
object JavaEnums { def apply[C <: Enum[C]](key: String, clzz: java.lang.Class[C]) = InputArg(key, new ListArg[C] with ToJavaEnum[C] { val clazz = clzz } ) }
  
object JavaEnums0{ def apply[C <: Enum[C]](key: String, clzz: java.lang.Class[C]) = InputArg(key, new ListArg0[C] with ToJavaEnum[C] { val clazz = clzz } ) }
  
object PJavaEnum { def apply[C <: Enum[C]](pos: Int, clzz: java.lang.Class[C])    = InputArg("-", new ToJavaEnum[C] with PositionalArg[C]{ val clazz = clzz ; val position = pos}) }
