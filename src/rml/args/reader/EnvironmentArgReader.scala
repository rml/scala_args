package rml.args.reader

import collection.JavaConversions._

object EnvironmentArgReader extends PrefixArgReader {

  def getMap = Map.empty ++ System.getenv()
}