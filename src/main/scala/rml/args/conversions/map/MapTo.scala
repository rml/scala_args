package rml.args.conversions.map

import rml.args.arg._
import rml.args.arg.input._
import rml.args.exceptions.IllegalArgException
import rml.args.arg.restriction.FixRestriction
import rml.args.arg.input.SingleArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.ListArg
import rml.args.arg.input.JoinArg

class MapTo[T](val map: Map[String, T]) {
  
  def mapToType(value: String): T = map(value)
  
  val getRestriction = FixRestriction(map.keySet)
  
  val baseType: String = "Map"

  override def toString = map.keys.toList.sorted.mkString("[", ", ", "]")
}


object AMap { def apply[T](key: String, map: Map[String, T]) = InputArg(key, new MapTo(map) with SingleArg[T]) }
 
object JMap { def apply[T](key: String, map: Map[String, T]) = InputArg(key, new MapTo(map) with JoinArg[T]) }
  
object Maps { def apply[T](key: String, map: Map[String, T]) = InputArg(key, new MapTo(map) with ListArg[T]) }
  
object Maps0{ def apply[T](key: String, map: Map[String, T]) = InputArg(key, new MapTo(map) with ListArg0[T]) }
  
object PMap { def apply[T](pos: Int,    map: Map[String, T]) = InputArg("-", new MapTo(map) with PositionalArg[T]{ val position = pos }) }
