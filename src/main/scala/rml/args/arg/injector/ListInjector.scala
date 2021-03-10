package rml.args.arg.injector

import rml.args.arg.{Arg, InputArg, InputMapper}
import rml.args.config.FullConfig

import scala.util.Try

abstract class ListInjector[T](
    arg: Arg[T],
    val valueArg: InputArg[List[String]],
    val keyx: Option[String] = None
) extends Injector
    with InputMapper[List[T]] {

  val args = List(arg, valueArg)

  def inputArgs(): Set[InputArg[_]] = Set(valueArg)

  override def apply(
      config: FullConfig,
      key: String,
      aliases: List[String]
  ): Try[List[T]] = {

    valueArg(config).map { list =>
      val kkey: String = keyx.getOrElse(valueArg.key)

      list.map { value => arg(inject(config, kkey, value)).get }
    }
  }

}
