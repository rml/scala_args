package rml.args.config.reader

import java.io.File
import scala.io.Source


case class FileArgReader(propFile: File) extends PrefixArgReader {

  def getMap: Map[String, String] = {
      
      val source = Source.fromFile(propFile)
      
      try {
        
    	  for{
    		  l <- source.getLines().toList
    		  line = l.trim if !(line.isEmpty || line.startsWith("#"))
    	  } yield {
    		  val split = line.span(_ != '=')
    				  (split._1.trim, split._2.substring(1).trim)
    	  }
      } finally {
        
        source.close
      }
      
      
  }.toMap
}
