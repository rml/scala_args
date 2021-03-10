package rml.args.conversions.map

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.FixRestriction
import rml.args.exceptions.IllegalArgException

trait ToEnum[E <: Enumeration] {

  val enum: E

  def mapToType(value: String): E#Value = try {
    enum.withName(value)
  } catch {
    case nsee: NoSuchElementException =>
      throw new IllegalArgException(
        "",
        value,
        (0 until enum.maxId).toList.map(enum(_).toString)
      )
  }

  lazy val getRestriction: FixRestriction = FixRestriction(
    enum.values.map(_.toString)
  )

  lazy val baseType: String = "Enumeration " + enum.getClass.getName

  override def toString: String = enum.values.mkString("[", ", ", "]")
}

object AnEnum {
  def apply[E <: Enumeration](key: String, e: E): InputArg[E#Value] =
    InputArg(key, new SingleArg[E#Value] with ToEnum[E] { val enum: E = e })
}

object JEnum {
  def apply[E <: Enumeration](key: String, e: E): InputArg[E#Value] =
    InputArg(key, new JoinArg[E#Value] with ToEnum[E] { val enum: E = e })
}

object Enums {
  def apply[E <: Enumeration](key: String, e: E): InputArg[List[E#Value]] =
    InputArg(key, new ListArg[E#Value] with ToEnum[E] { val enum: E = e })
}

object Enums0 {
  def apply[E <: Enumeration](key: String, e: E): InputArg[List[E#Value]] =
    InputArg(key, new ListArg0[E#Value] with ToEnum[E] { val enum: E = e })
}

object PEnum {
  def apply[E <: Enumeration](pos: Int, e: E): InputArg[E#Value] = InputArg(
    "-",
    new ToEnum[E] with PositionalArg[E#Value] {
      val enum: E = e; val position: Int = pos
    }
  )
}
