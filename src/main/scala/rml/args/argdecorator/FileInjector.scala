package rml.args.argdecorator

import rml.args.arg.InputArg
import rml.args.conversions.files.Files
import rml.args.arginjector.ListInjector
import rml.args.arginjector.DataFromCsv
import rml.args.arginjector.SingleInjector
import rml.args.arg.DependentArg

case class FileInjector[T](
    override val arg: DependentArg[T], 
    files: Files, 
    override val valueArg: InputArg[String],
    override val key: Option[String] = None
) extends SingleInjector(arg, valueArg, key) with DataFromCsv