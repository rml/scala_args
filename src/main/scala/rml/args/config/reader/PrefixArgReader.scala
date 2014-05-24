package rml.args.config.reader

import com.typesafe.scalalogging.slf4j.LazyLogging

trait PrefixArgReader extends LazyLogging {

  def filterPrefix(prefix: String) = {s: String => 
    val accept = s.startsWith(prefix)
    
    logger.debug(s"$s $accept $prefix")
    
    accept
  }
  
  def removePrefix(prefix: String) = {s: String => s.replaceFirst("^" + prefix, "").toLowerCase}
  
  def prefix(filterprefix: String): Map[String, List[String]] = get(filterPrefix(filterprefix), removePrefix(filterprefix))

  def get(filter: String => Boolean, translator: String => String): Map[String, List[String]] = {
    getMap
    .filter{case(k,v) => filter(k)}
    .map{case(k,v) => (translator(k), CommandlineArgReader.parseArgumentValues(v))}
  }
  
  def all(): Map[String, List[String]] = getMap.map{case(k,v) => (k, CommandlineArgReader.parseArgumentValues(v))}
  
  def apply() = all()
  
  def getMap: Map[String, String]
  
}