package rml.args.arginjector

import rml.args.arg.Arg
import rml.args.conversions.files.Files
import rml.args.conversions.strings.Strings
import rml.args.util.CsvReader
import rml.args.exceptions.IllegalArgException

trait DataFromCsv extends Injector {

  val files: Files
  
  override def inject(argMap: Map[String, List[String]], key: Map[String, String]): Map[String, List[String]] = {

    files.apply(argMap).foreach{ file =>
      CsvReader.findKey(file, key) match {
        case Some(m) => return m.map{case(k,v) => (k,List(v))} ++ argMap
        case None =>
      }
    }
    argMap
  }
}