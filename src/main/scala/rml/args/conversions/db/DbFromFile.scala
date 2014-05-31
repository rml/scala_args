package rml.args.conversions.db

import java.io.File

import rml.args.arg.Arg
import rml.args.arg.FuncArg
import rml.args.arg.InputArg
import rml.args.arg.Mapper
import rml.args.arg.decorator.FileInjector
import rml.args.arg.decorator.ListFileInjector
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.Strings
import rml.args.config.FullConfig

object DbFromFile {
  
  def apply(valueArg: String, files: Arg[List[File]]): FuncArg[(String, String, String)] = 
    apply(AString(valueArg), files, None)
  
  def apply(valueArg: InputArg[String], files: Arg[List[File]], key: Option[String] = None): FuncArg[(String, String, String)] = {

    val injector = FileInjector(Db(), files, valueArg, key)
    
    val mapper = Mapper[(String, String, String)](List(valueArg, files)){ config: FullConfig =>

      injector(config, "", List.empty)
    }

    FuncArg(mapper)
  }
  
}

object DbsFromFile {
  
  def apply(loop: String, files: Arg[List[File]]): FuncArg[List[(String, String, String)]] = 
    apply(Strings(loop), files, None)
  
  def apply(loop: InputArg[List[String]], files: Arg[List[File]], key: Option[String] = None): FuncArg[List[(String, String, String)]] = {
    
    val injector = ListFileInjector(Db(), files, loop, key)
    
    val mapper = Mapper[List[(String, String, String)]](List(loop, files)){ config: FullConfig =>

      injector(config, "", List.empty)
    }

    FuncArg(mapper)
  }
  
}
