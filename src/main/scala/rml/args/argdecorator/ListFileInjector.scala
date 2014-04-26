package rml.args.argdecorator

import rml.args.arg.InputArg
import rml.args.conversions.files.Files
import rml.args.arginjector.ListInjector
import rml.args.arginjector.DataFromCsv
import rml.args.arg.DependentArg

case class ListFileInjector[T](
    val arg: DependentArg[T], 
    files: Files, 
    override val valueArg: InputArg[List[String]],
    override val key: Option[String] = None
) extends ListInjector(arg, valueArg, key) with DataFromCsv