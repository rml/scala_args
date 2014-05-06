package rml.args.reader

import collection.JavaConversions._

object SystemPropertiesArgReader extends PrefixArgReader {

  // maybe switch to scala.sys.SystemProperties later
  def getMap = Map.empty ++ System.getProperties()
}