package rml.args.conversions.map

import rml.args.arg._
import rml.args.arg.input._
import rml.args.exceptions.IllegalArgException
import rml.args.arg.InputArg
import rml.args.arg.restriction.FixRestriction
import rml.args.arg.input.SingleArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.ListArg
import rml.args.arg.input.JoinArg

class DynMapTo[T](map: () => Map[String, T]) {
  
  val baseType: String = "Map"
    
  def mapToType(value: String): T = map()(value)
  
  def getRestriction = {
    println(map().keySet)
    FixRestriction(map().keySet)
  }

  override def toString = map().keys.toList.sorted.mkString("[", ", ", "]")
}


object ADynMap { def apply[T](key: String, map: () => Map[String, T]) = InputArg(key, new DynMapTo(map) with SingleArg[T]) }
 
object JDynMap { def apply[T](key: String, map: () => Map[String, T]) = InputArg(key, new DynMapTo(map) with JoinArg[T]) }
  
object DynMaps { def apply[T](key: String, map: () => Map[String, T]) = InputArg(key, new DynMapTo(map) with ListArg[T]) }
  
object DynMaps0{ def apply[T](key: String, map: () => Map[String, T]) = InputArg(key, new DynMapTo(map) with ListArg0[T]) }
  
object DynPMap { def apply[T](pos: Int,    map: () => Map[String, T]) = InputArg("-", new DynMapTo(map) with PositionalArg[T]{ val position = pos }) }

