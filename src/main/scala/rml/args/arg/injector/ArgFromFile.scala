package rml.args.arg.injector

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
import rml.args.util.CsvReader
import scala.util.Try

class ArgFromFile[T](resultArg: Arg[T]) {
  
  def apply(valueArg: String, files: Arg[List[File]]): FuncArg[T] = 
    apply(AString(valueArg), files, None)
  
  def apply(valueArg: InputArg[String], files: Arg[List[File]], key: Option[String] = None): FuncArg[T] = {

    val injector = FileInjector(resultArg, files, valueArg, key)
    
    val mapper = Mapper[T](valueArg, files){ config: FullConfig =>

      injector(config, "", List.empty)
    }

    FuncArg(mapper)
  }
  
}

class ArgsFromFile[T](resultArg: Arg[T]) {
  
  def apply(loop: String, files: Arg[List[File]]): FuncArg[List[T]] = 
    apply(Strings(loop), files, None)
  
  def apply(loop: InputArg[List[String]], files: Arg[List[File]], key: Option[String] = None): FuncArg[List[T]] = {
    
    val injector = ListFileInjector(resultArg, files, loop, key)
    
    val mapper = Mapper[List[T]](loop, files){ config: FullConfig =>

      injector(config, "", List.empty)
    }

    FuncArg(mapper)
  }
  
}

class ArgMapFromFile[T](resultArg: Arg[T]) {
  
  def apply(key: String, files: Arg[List[File]]): FuncArg[Map[String, T]] = {
    
    val mapper = Mapper[Map[String, T]](files){ config: FullConfig =>

      def csvEntryToArg(csvEntry: Map[String, List[String]]): Try[(String, T)] = {
        
        val entryResult: Try[T] = resultArg(config.over(csvEntry))
        val entryKey: String = csvEntry.getOrElse(key, Nil).mkString(" ")
        entryResult.map((entryKey, _))
      }
      
      def fileToArgMap(file: File): Map[String, T] = {

        val successEntries = CsvReader.rowsAsMaps(file).map(csvEntryToArg).filter(_.isSuccess)
        successEntries.map(_.get).toMap
      }

      files.apply(config).map{ filelist: List[File] =>
        filelist.reverse.map(fileToArgMap).foldLeft(Map[String, T]())(_ ++ _)
      }
    }

    FuncArg(mapper)
  }
  
}
  
class ArgListFromFile[T](resultArg: Arg[T]) {
  
  def apply(files: Arg[List[File]]): FuncArg[List[T]] = {
    
    val mapper = Mapper[List[T]](files){ config: FullConfig =>

      def csvEntryToArg(csvEntry: Map[String, List[String]]): Try[T] = {
        resultArg(config.over(csvEntry))
      }
      
      def fileToArgList(file: File): List[T] = {

        val successEntries = CsvReader.rowsAsMaps(file).map(csvEntryToArg).filter(_.isSuccess)
        successEntries.map(_.get)
      }

      files.apply(config).map{ filelist: List[File] =>
        filelist.map(fileToArgList).foldLeft(List[T]())(_ ::: _)
      }
    }

    FuncArg(mapper)
  }
  
}
