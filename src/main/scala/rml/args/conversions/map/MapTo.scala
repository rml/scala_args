package rml.args.conversions.map

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException

class MapTo[T](map: Map[String, T]) extends SetRestriction {
  def mapToType(value: String): T = map(value)

  def allowed = map.keySet
    
  override def toString = map.keys.toList.sorted.mkString("[", ", ", "]")
}

case class AMapTo[T](key: String, map: Map[String, T]) extends MapTo(map) with SingleArg[T]

case class MapTos[T](key: String, map: Map[String, T]) extends MapTo(map) with ListArg[T]

case class MapTos0[T](key: String, map: Map[String, T]) extends MapTo(map) with List0Arg[T]

case class PMapTo[T](pos: Int, map: Map[String, T]) extends MapTo(map) with PositionalArg[T]
