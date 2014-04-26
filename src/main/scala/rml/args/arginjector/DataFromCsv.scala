package rml.args.arginjector

import rml.args.arg.Arg
import rml.args.conversions.files.Files
import rml.args.conversions.strings.Strings
import rml.args.util.CsvReader
import rml.args.exceptions.IllegalArgException
import rml.args.domain.FullConfig

trait DataFromCsv extends Injector {

  val files: Files
  
  override def inject(config: FullConfig, key: Map[String, String]): FullConfig = {

    files.apply(config).foreach{ file =>
      CsvReader.findKey(file, key) match {
        case Some(m) => return config.over(m.map{case(k,v) => (k,List(v))})
        case None =>
      }
    }
    config
  }
}