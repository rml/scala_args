package rml.args.conversions.map

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.FixRestriction

class DynMapTo[T](map: () => Map[String, T]) {

  val baseType: String = "Map"

  def mapToType(value: String): T = map()(value)

  def getRestriction: FixRestriction = {
    FixRestriction(map().keySet)
  }

  override def toString: String =
    map().keys.toList.sorted.mkString("[", ", ", "]")
}

object ADynMap {
  def apply[T](key: String, map: () => Map[String, T]): InputArg[T] =
    InputArg(key, new DynMapTo(map) with SingleArg[T])
}

object JDynMap {
  def apply[T](key: String, map: () => Map[String, T]): InputArg[T] =
    InputArg(key, new DynMapTo(map) with JoinArg[T])
}

object DynMaps {
  def apply[T](key: String, map: () => Map[String, T]): InputArg[List[T]] =
    InputArg(key, new DynMapTo(map) with ListArg[T])
}

object DynMaps0 {
  def apply[T](key: String, map: () => Map[String, T]): InputArg[List[T]] =
    InputArg(key, new DynMapTo(map) with ListArg0[T])
}

object DynPMap {
  def apply[T](pos: Int, map: () => Map[String, T]): InputArg[T] = InputArg(
    "-",
    new DynMapTo(map) with PositionalArg[T] { val position: Int = pos }
  )
}
