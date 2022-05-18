package rml.args.conversions.map

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.FixRestriction
import rml.args.exceptions.IllegalArgException

trait ToEnum[E <: Enumeration] {

  val anEnum: E

  def mapToType(value: String): E#Value = try {
    anEnum.withName(value)
  } catch {
    case nsee: NoSuchElementException =>
      throw new IllegalArgException(
        "",
        value,
        (0 until anEnum.maxId).toList.map(anEnum(_).toString)
      )
  }

  lazy val getRestriction: FixRestriction = FixRestriction(
    anEnum.values.map(_.toString)
  )

  lazy val baseType: String = "Enumeration " + anEnum.getClass.getName

  override def toString: String = anEnum.values.mkString("[", ", ", "]")
}

object AnEnum {
  def apply[E <: Enumeration](key: String, e: E): InputArg[E#Value] =
    InputArg(key, new SingleArg[E#Value] with ToEnum[E] { val anEnum: E = e })
}

object JEnum {
  def apply[E <: Enumeration](key: String, e: E): InputArg[E#Value] =
    InputArg(key, new JoinArg[E#Value] with ToEnum[E] { val anEnum: E = e })
}

object Enums {
  def apply[E <: Enumeration](key: String, e: E): InputArg[List[E#Value]] =
    InputArg(key, new ListArg[E#Value] with ToEnum[E] { val anEnum: E = e })
}

object Enums0 {
  def apply[E <: Enumeration](key: String, e: E): InputArg[List[E#Value]] =
    InputArg(key, new ListArg0[E#Value] with ToEnum[E] { val anEnum: E = e })
}

object PEnum {
  def apply[E <: Enumeration](pos: Int, e: E): InputArg[E#Value] = InputArg(
    "-",
    new ToEnum[E] with PositionalArg[E#Value] {
      val anEnum: E = e; val position: Int = pos
    }
  )
}
