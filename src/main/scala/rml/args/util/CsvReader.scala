package rml.args.util

import java.io.File
import scala.io.Source

/**
 * Minimal temporary implementation, without any error handling
 */
object CsvReader {

  def read(file: File) = {

    val source = Source.fromFile(file)
    
    try {
    	val lines = source.getLines
    	val cols = lines.next.split("\t")
    	lines.map(l => cols.zip(l.split("\t")).toMap)
    } finally {
      
      source.close
    }
    
  }
 
  def findKey(file: File, keys: Map[String, String]): Option[Map[String, String]] = {
    
    read(file).foreach{ m =>
      if(keys.forall{case(k,v) => m.contains(k) && m(k) == v}) return Some(m)
    }
    
    None
  }
  
}