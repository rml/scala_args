package rml.args.config.reader

import collection.JavaConversions._

object SystemPropertiesArgReader extends PrefixArgReader {

  // maybe switch to scala.sys.SystemProperties later
  def getMap = Map.empty ++ System.getProperties()
}