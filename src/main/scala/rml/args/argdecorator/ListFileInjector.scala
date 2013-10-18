package rml.args.argdecorator

import rml.args.arg.Arg
import rml.args.conversions.files.Files
import rml.args.arginjector.ListInjector
import rml.args.arginjector.DataFromCsv

case class ListFileInjector[T](
    arg: Arg[T], 
    files: Files, 
    key: String    
) extends ListInjector(arg, key) with DataFromCsv