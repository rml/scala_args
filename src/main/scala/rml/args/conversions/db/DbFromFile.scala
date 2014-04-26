package rml.args.conversions.db

import rml.args.arg.InputArg
import rml.args.argdecorator.FileInjector
import rml.args.argdecorator.ListFileInjector
import rml.args.conversions.files.Files
import rml.args.arg.DependentArg
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.Strings

object DbFromFile {
  
  def apply(valueArg: String, files: Files): DependentArg[(String, String, String)] = 
    apply(AString(valueArg), files, None)
  
  def apply(valueArg: InputArg[String], files: Files, key: Option[String] = None): DependentArg[(String, String, String)] = 
    FileInjector(Db(), files, valueArg, key)
  
}

object DbsFromFile {
  
  def apply(loop: String, files: Files): DependentArg[List[(String, String, String)]] = 
    apply(Strings(loop), files, None)
  
  def apply(loop: InputArg[List[String]], files: Files, key: Option[String] = None): DependentArg[List[(String, String, String)]] = 
    ListFileInjector(Db(), files, loop, key)
  
}
