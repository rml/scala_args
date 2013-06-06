package rml.args.util

import scala.reflect.io.File

/**
 * Minimal temporary implementation, without any error handling
 */
object CsvReader {

  def read(file: File) = {
    
    val lines = file.lines
    val cols = lines.next.split("\t")
    lines.map(l => cols.zip(l.split("\t")).toMap)
  }
 
  def findKey(file: File, keys: Map[String, String]): Option[Map[String, String]] = {
    
    read(file).foreach{ m =>
      if(keys.forall{case(k,v) => m.contains(k) && m(k) == v}) return Some(m)
    }
    
    None
  }
  
}