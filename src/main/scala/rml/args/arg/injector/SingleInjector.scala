package rml.args.arg.injector
import rml.args.arg.{Arg, InputArg, InputMapper}
import rml.args.config.FullConfig

import scala.util.Try

abstract class SingleInjector[T](
    val arg: Arg[T],
    val valueArg: InputArg[String],
    val keyx: Option[String] = None
) extends Injector
    with InputMapper[T] {

  val args = List(arg, valueArg)

  def inputArgs(): Set[InputArg[_]] = Set(valueArg)

  override def apply(
      config: FullConfig,
      key: String,
      aliases: List[String]
  ): Try[T] = {

    valueArg(config).flatMap { value =>
      arg(inject(config, keyx.getOrElse(valueArg.key), value))
    }
  }

}
