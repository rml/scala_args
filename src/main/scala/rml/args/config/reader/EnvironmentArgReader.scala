package rml.args.config.reader

import collection.JavaConversions._

object EnvironmentArgReader extends PrefixArgReader {

  def getMap = Map.empty ++ System.getenv()
}