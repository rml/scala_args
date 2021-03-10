package rml.args.config.reader

import scala.jdk.CollectionConverters._

object EnvironmentArgReader extends PrefixArgReader {

  def getMap: Map[String, String] = Map.empty ++ System.getenv().asScala
}
