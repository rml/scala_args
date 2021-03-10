package rml.args.config.reader

import scala.jdk.CollectionConverters._

object SystemPropertiesArgReader extends PrefixArgReader {

  // maybe switch to scala.sys.SystemProperties later
  def getMap: Map[String, String] = Map.empty ++ System.getProperties.asScala
}
