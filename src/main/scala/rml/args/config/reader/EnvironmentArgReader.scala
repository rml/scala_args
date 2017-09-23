package rml.args.config.reader

import collection.JavaConverters._

object EnvironmentArgReader extends PrefixArgReader {

  def getMap = Map.empty ++ System.getenv().asScala
}