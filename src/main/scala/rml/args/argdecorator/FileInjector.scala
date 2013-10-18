package rml.args.argdecorator

import rml.args.arg.Arg
import rml.args.conversions.files.Files
import rml.args.arginjector.ListInjector
import rml.args.arginjector.DataFromCsv
import rml.args.arginjector.SingleInjector

case class FileInjector[T](
    override val arg: Arg[T], 
    files: Files, 
    ref: String
) extends SingleInjector(arg, ref) with DataFromCsv