package rml.args.conversions.map

import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg
import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg
import rml.args.arg.SetRestriction

trait ToJavaEnum[C <: Enum[C]] extends SetRestriction {
    
  val clazz: java.lang.Class[C]
  def mapToType(value: String) = Enum.valueOf(clazz, value)
  
  def allowed = clazz.getEnumConstants().map(_.toString).toSet
  
  override def toString = clazz.getEnumConstants().mkString(clazz.getName() + "[", ", ", "]")
}

case class AJavaEnum[C <: Enum[C]](key: String, clazz: java.lang.Class[C]) extends ToJavaEnum[C] with SingleArg[C]

case class JavaEnums[C <: Enum[C]](key: String, clazz: java.lang.Class[C]) extends ToJavaEnum[C] with ListArg[C]

case class JavaEnums0[C <: Enum[C]](key: String, clazz: java.lang.Class[C]) extends ToJavaEnum[C] with List0Arg[C]

case class PJavaEnum[C <: Enum[C]](pos: Int, clazz: java.lang.Class[C]) extends ToJavaEnum[C] with PositionalArg[C]