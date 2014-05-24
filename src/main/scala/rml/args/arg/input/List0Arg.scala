package rml.args.arg.input
import rml.args.arg.InputCmdMapper
import rml.args.config.FullConfig
import scala.util.Try
import scala.util.Success

/**
 * Maps each argument of a list individually and returns a list
 * If the argument is missing, it is treated as a list of length 0
 */
trait ListArg0[T] extends ListArg[T] {

  override type R = T

  override val structureInfo: String = "List0[%s]"
    
  override def apply(config: FullConfig, key: String, aliases: List[String]): Try[List[T]] = {
    
    super.apply(config, key, aliases).orElse(Success(List()))
  }
}

