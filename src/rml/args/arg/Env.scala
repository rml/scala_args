package rml.args.arg
import rml.args.reader.CommandlineArgReader
import scala.collection.immutable.TreeMap

/**
 * Reads an argument from an environment variable. This is usually used to read
 * general arguments like HOME etc.
 * Type information is taken from the wrapped argument.
 */
case class Env[T](arg: Arg[T]) extends Arg[T] {
  
  override val key = arg.key

  override val desc: String = arg.desc
    
  override def allKeysFound(argMap: Map[String, List[String]]) = System.getenv(key) != null

  override def apply(argMap: Map[String, List[String]]): T = if(allKeysFound(argMap)){
    
    arg.apply(Map(arg.key -> CommandlineArgReader.parseArgumentValues(System.getenv(key))))
    
  } else {
    
    import scala.collection.JavaConversions._
    val envvars = TreeMap.empty[String, String] ++ System.getenv
    throw new IllegalArgumentException("The environment variable " + key + " could not be found. Found only " + envvars.keys.mkString("\n", ", ", ""))
  }
}