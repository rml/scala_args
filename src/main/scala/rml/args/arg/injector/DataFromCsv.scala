package rml.args.arg.injector

import scala.reflect.io.File
import rml.args.arg.Arg
import rml.args.config.FullConfig
import rml.args.util.CsvReader

trait DataFromCsv extends Injector {

  val files: Arg[List[File]]
  
  override def inject(config: FullConfig, key: Map[String, String]): FullConfig = {

    files.apply(config).get.foreach{ file =>
      CsvReader.findKey(file, key) match {
        case Some(m) => return config.over(m.map{case(k,v) => (k,List(v))})
        case None =>
      }
    }
    config
  }
}