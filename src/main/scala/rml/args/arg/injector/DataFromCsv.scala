package rml.args.arg.injector

import java.io.File
import rml.args.arg.Arg
import rml.args.config.FullConfig
import rml.args.util.CsvReader

trait DataFromCsv extends Injector {

  val files: Arg[List[File]]
  
  override def inject(config: FullConfig, keyColumn: String, keyValue: String): FullConfig = {

    files.apply(config).get.foreach{ file =>
      CsvReader.findKey(file, keyColumn, keyValue) match {
        case Some(m) => return config.over(m)
        case None =>
      }
    }
    config
  }
}

