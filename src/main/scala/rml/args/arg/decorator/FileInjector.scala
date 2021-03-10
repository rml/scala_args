package rml.args.arg.decorator
import rml.args.arg.{Arg, InputArg}
import rml.args.arg.injector.{DataFromCsv, SingleInjector}

import java.io.File

case class FileInjector[T](
    override val arg: Arg[T],
    files: Arg[List[File]],
    override val valueArg: InputArg[String],
    key: Option[String] = None
) extends SingleInjector(arg, valueArg, key)
    with DataFromCsv
