package rml.args.conversions.db

import rml.args.arg.MultiArg
import rml.args.conversions.strings.AString
import rml.args.arg.Arg
import rml.args.conversions.files.Files
import rml.args.argdecorator.FileInjector
import rml.args.argdecorator.ListFileInjector

object DbFromFile {
  
  def apply(key: String, files: Files): Arg[(String, String, String)] = FileInjector(Db(), files, key)
  
}

object DbsFromFile {
  
  def apply(loop: String, files: Files): Arg[List[(String, String, String)]] = ListFileInjector(Db(), files, loop)
  
}
