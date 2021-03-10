package rml.args.util

import java.io.File
import scala.io.Source
import rml.args.config.CmdLineArgs
import rml.args.config.reader.CommandlineArgReader

/**
 * Minimal temporary implementation, without any error handling
 */
object CsvReader {
  
  def parseRowArgs(row: Map[String, String]): Map[String, List[String]] = {

    for((k, v) <- row) yield {
      (k, CommandlineArgReader.parseArgumentValues(v))
    }
  }
  

  def rowsAsMaps(file: File): List[Map[String, List[String]]] = {

    rowsAsRawMaps(file).map(parseRowArgs)
  }
 
  
  def rowsAsRawMaps(file: File): List[Map[String, String]] = {

    val source = Source.fromFile(file)
    
    try {
    	val lines = source.getLines()
    	val cols = lines.next().split("\t")
    	lines.map(l => cols.zip(l.split("\t")).toMap).toList
    } finally {
      
      source.close
    }
    
  }
 
  
  def findKey(file: File, keyColumn: String, keyValue: String): Option[Map[String, List[String]]] = {
    
    for(row <- rowsAsRawMaps(file) if row.contains(keyColumn) && row(keyColumn) == keyValue){
      return Some(parseRowArgs(row))
    }
    
    None
  }
  
  
  
}