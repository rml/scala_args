package rml.args.arg.injector
import rml.args.config.FullConfig
import rml.args.arg.InputArg
import rml.args.arg.Arg
import scala.util.Try
import rml.args.arg.InputMapper

abstract class SingleInjector[T](val arg: Arg[T], val valueArg: InputArg[String], val keyx: Option[String] = None) extends Injector with InputMapper[T] {

  val args = List(arg, valueArg)
  
  def inputArgs(): Set[InputArg[_]] = Set(valueArg)
  
  override def apply(config: FullConfig, key: String, aliases: List[String]): Try[T] = {
    
    valueArg(config).flatMap{ value =>
      
      val injectValues = Map(keyx.getOrElse(valueArg.key) -> value)
      
      arg(inject(config, injectValues))
    }
  }

}