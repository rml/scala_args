package rml.args.reader

import scala.reflect.io.File


case class FileArgReader(propFile: File) extends PrefixArgReader {

  def getMap = (
      
      for{
        l <- File(propFile).lines
        line = l.trim if !(line.isEmpty || line.startsWith("#"))
      } yield {
        val split = line.span(_ != '=')
        (split._1.trim, split._2.substring(1).trim)
      }
      
  ).toMap
}
