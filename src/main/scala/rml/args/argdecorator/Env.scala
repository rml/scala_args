package rml.args.argdecorator

import rml.args.arg.Arg
import rml.args.reader.CommandlineArgReader
import scala.collection.immutable.TreeMap
import rml.args.exceptions.IllegalArgException

/**
 * Reads an argument from an environment variable. This is usually used to read
 * general arguments like HOME etc.
 * Type information is taken from the wrapped argument.
 */
case class Env[T](arg: Arg[T]) extends ArgDelegator[T] {
  
  override def showdesc: String = "Env: $" + key + "=" + System.getenv(key)
  
  override def noInformationMissing(argMap: Map[String, List[String]]) = System.getenv(key) != null

  override def apply(argMap: Map[String, List[String]]): T = if(noInformationMissing(argMap)){
    
    arg.apply(Map(arg.key -> CommandlineArgReader.parseArgumentValues(System.getenv(key))))
    
  } else {
    
    import scala.collection.JavaConversions._
    val envvars = TreeMap.empty[String, String] ++ System.getenv
    throw new IllegalArgException("The environment variable " + key + " could not be found. Found only " + envvars.keys.mkString("\n", ", ", ""))
  }
}