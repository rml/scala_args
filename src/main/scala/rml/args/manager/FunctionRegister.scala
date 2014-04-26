package rml.args.manager

import rml.args.domain.Function
import rml.args.domain.FullConfig
import rml.args.domain.Func
import rml.args.argdecorator.Opt
import rml.args.conversions.strings.PString
import rml.args.exceptions.IllegalArgException
import rml.args.reader.ConfReader
import rml.args.conversions.strings.PString
import rml.args.arg.Flag
import rml.args.conversions.strings.JString
import rml.args.argmapper.PositionalArg
import rml.args.arg.Arg
import rml.args.exceptions.FunctionNotFoundException
import com.typesafe.scalalogging.slf4j.Logging

/**
 * Central register for function definitions
 */
object FunctionRegister extends Logging {

  import logger._
  
  /**
   * Function definitions are stored in this mutable map
   */
  private val register = scala.collection.mutable.Map[List[String], Function[_]]()
  
  def commands(sep: String = " "): List[String] = register.keySet.map(_.mkString(sep)).toList.sorted

  def list(filter: String): List[(String, Function[_])] = register.filter{case(k, v) => k.mkString(" ") contains filter}.map{case(k, v) => (k.mkString(" "), v)}.toList.sortBy(_._1)

  /**
   * Register a new function
   */
  def set(key: List[String], fdef: Function[_]): Unit = {
    if(register.contains(key))
      throw new IllegalStateException("Function for key " + key.mkString("[", "][", "]") + " already registered. (" + fdef + ")")
    register.put(key, fdef)
  }

  /**
   * Remove a function from the register
   * Returns the function if it was previously registered, otherwise None
   */
  def remove(key: List[String]): Option[Function[_]] = register.remove(key)

  /**
   * Replace a function with a new one
   * Returns the old function if it was previously registered, otherwise None
   */
  def replace(key: List[String], fdef: Function[_]): Option[Function[_]] = {
    val old = remove(key)
    set(key, fdef)
    old
  }

  /**
   * Returns the function definition, that best matches the key.
   */
  def get(key: List[String]): Function[_] = {
    
    findLongestMatching(key) match {
      case Some(k) => register(k)
      case None => throw new IllegalArgException
    }
  }

  /**
   * Finds the longest registered key, that is fully included in the searched key.
   * Comparison always starts from the root, never in the middle of the key.
   */
  def findLongestMatching(key: List[String]): Option[List[String]] = {

    for(i <- key.length.to(1, -1) ){

      val partkey = key.take(i)
      if(register.contains(partkey))
        return Some(partkey)

    }
    None
  }

  /**
   * Tries to find an exact match for the key and returns the respective function definition, if one exists.
   */
  def getOption(key: List[String]): Option[Function[_]] = register.get(key)

  /**
   * Checks, whether the key is registered
   */
  def isRegistered(key: List[String]): Boolean = register.contains(key)
  
  /**
   * Convenience function
   */
  def apply(key: List[String]) = get(key)
  
  /**
   * Convenience function
   */
  def apply(key: String) = get(key :: Nil)
  
  /**
   * Convenience function
   */
  def update(key: List[String], fdef: Function[_]) = set(key, fdef)
  
  /**
   * Convenience function
   */
  def update(key: String, fdef: Function[_]) = set(key :: Nil, fdef)
  
}
