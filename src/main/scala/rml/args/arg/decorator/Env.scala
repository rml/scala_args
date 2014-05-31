package rml.args.arg.decorator
import rml.args.config.reader.CommandlineArgReader
import rml.args.exceptions.IllegalArgException
import rml.args.config.FullConfig
import rml.args.arg.InputArg
import scala.util.Failure
import rml.args.config.reader.CommandlineArgReader

/**
 * Reads an argument from an environment variable. This is usually used to read
 * general arguments like HOME etc.
 * Type information is taken from the wrapped argument.
 */
object Env {
  
  def apply[T](arg: InputArg[T]): InputArg[T] = arg.mapLowLevel { (arg: InputArg[T], config: FullConfig) =>  
    
    val envVar = System.getenv(arg.key)
      
    if(envVar == null){
      Failure(new IllegalArgException(s"No values found for environment variable ${arg.key} found"))
    } else {
      val configWithEnv = config.over(arg.key, CommandlineArgReader.parseArgumentValues(envVar))
      arg.apply(configWithEnv)
    }
  }  
}
