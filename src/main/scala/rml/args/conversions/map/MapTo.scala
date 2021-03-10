package rml.args.conversions.map

import rml.args.arg._
import rml.args.arg.input._
import rml.args.arg.restriction.FixRestriction

class MapTo[T](val map: Map[String, T]) {

  def mapToType(value: String): T = map(value)

  val getRestriction: FixRestriction = FixRestriction(map.keySet)

  val baseType: String = "Map"

  override def toString: String =
    map.keys.toList.sorted.mkString("[", ", ", "]")
}

object AMap {
  def apply[T](key: String, map: Map[String, T]): InputArg[T] =
    InputArg(key, new MapTo(map) with SingleArg[T])
}

object JMap {
  def apply[T](key: String, map: Map[String, T]): InputArg[T] =
    InputArg(key, new MapTo(map) with JoinArg[T])
}

object Maps {
  def apply[T](key: String, map: Map[String, T]): InputArg[List[T]] =
    InputArg(key, new MapTo(map) with ListArg[T])
}

object Maps0 {
  def apply[T](key: String, map: Map[String, T]): InputArg[List[T]] =
    InputArg(key, new MapTo(map) with ListArg0[T])
}

object PMap {
  def apply[T](pos: Int, map: Map[String, T]): InputArg[T] =
    InputArg(
      "-",
      new MapTo(map) with PositionalArg[T] { val position: Int = pos }
    )
}
