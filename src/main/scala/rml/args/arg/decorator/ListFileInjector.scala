package rml.args.arg.decorator
import rml.args.arg.{Arg, InputArg}
import rml.args.arg.injector.{DataFromCsv, ListInjector}

import java.io.File

case class ListFileInjector[T](
    arg: Arg[T],
    files: Arg[List[File]],
    override val valueArg: InputArg[List[String]],
    key: Option[String] = None
) extends ListInjector(arg, valueArg, key)
    with DataFromCsv
