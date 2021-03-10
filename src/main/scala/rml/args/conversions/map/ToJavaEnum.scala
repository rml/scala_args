package rml.args.conversions.map

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.FixRestriction

trait ToJavaEnum[C <: Enum[C]] {

  val clazz: java.lang.Class[C]
  def mapToType(value: String): C = Enum.valueOf(clazz, value)

  lazy val baseType: String = clazz.toString.replace("class", "Enum")

  lazy val getRestriction: FixRestriction = FixRestriction(
    clazz.getEnumConstants.map(_.toString).toSet
  )

  override def toString: String =
    clazz.getEnumConstants.mkString(clazz.getName + "[", ", ", "]")
}

object AJavaEnum {
  def apply[C <: Enum[C]](key: String, clzz: java.lang.Class[C]): InputArg[C] =
    InputArg(
      key,
      new SingleArg[C] with ToJavaEnum[C] { val clazz: Class[C] = clzz }
    )
}

object JJavaEnum {
  def apply[C <: Enum[C]](key: String, clzz: java.lang.Class[C]): InputArg[C] =
    InputArg(
      key,
      new JoinArg[C] with ToJavaEnum[C] { val clazz: Class[C] = clzz }
    )
}

object JavaEnums {
  def apply[C <: Enum[C]](
      key: String,
      clzz: java.lang.Class[C]
  ): InputArg[List[C]] =
    InputArg(
      key,
      new ListArg[C] with ToJavaEnum[C] { val clazz: Class[C] = clzz }
    )
}

object JavaEnums0 {
  def apply[C <: Enum[C]](
      key: String,
      clzz: java.lang.Class[C]
  ): InputArg[List[C]] =
    InputArg(
      key,
      new ListArg0[C] with ToJavaEnum[C] { val clazz: Class[C] = clzz }
    )
}

object PJavaEnum {
  def apply[C <: Enum[C]](pos: Int, clzz: java.lang.Class[C]): InputArg[C] =
    InputArg(
      "-",
      new ToJavaEnum[C] with PositionalArg[C] {
        val clazz: Class[C] = clzz; val position: Int = pos
      }
    )
}
