package rml.args.domain

import java.text.SimpleDateFormat
import java.util.Date

import com.typesafe.scalalogging.slf4j.{LazyLogging => Logging}

case class FullConfig(
    cmdConfig: Config,
    configs: List[Config],
    func: String, 
    subfuncs: List[String]  
) extends Logging {
  
  import logger._

  def over(key: String, vals: List[String]): FullConfig = over(Map(key -> vals))
    
  def over(args: Map[String, List[String]]): FullConfig = over(Config(args, new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date())))
  
  def over(config: Config): FullConfig = FullConfig(cmdConfig, config :: configs, func, subfuncs)
    
  val funcName = func :: subfuncs
  
  val allConfigs = cmdConfig :: configs
  
  // cmdArgs
  val cmdArgs = cmdConfig.args
  
  def isCmdArg(key: String): Boolean = cmdArgs.contains(key)
  
  def cmdArg(key: String): List[String] = cmdArgs(key)
  
  // posArgs
  val posArgs: Array[String] = cmdArgs.getOrElse("-", Nil).toArray
  
  val hasPosArgs: Boolean = !posArgs.isEmpty
  
  def posArg(pos: Int): String = if(pos < posArgs.length) posArgs(pos) else ""

  // args
  val args: Map[String, List[String]] = allConfigs.reverse.map(_.args).reduce(_ ++ _)
  
  def isArg(key: String): Boolean = args.contains(key)
  
  def arg(key: String): List[String] = args(key)
  
  def argWithAlias(key: String, aliases: String*): List[String] = {
    
    val keys = key :: aliases.toList
    
    debug("argWithAlias(%s)", keys.mkString(", "))

    for{
      conf <- allConfigs
      key <- keys
    }{
      val found = conf.args.contains(key)
      
      if(found){
        debug("Key {} with value {} found in config {}", key, conf.args(key), conf.origin)
        return conf.args(key)
      } else {
        debug("Key {} not found in config {}", key, conf.origin)
      }
    }
    
    debug("argWithAlias: Nothing found for: %s", keys.mkString(", "))
    
    Nil
  }

  def argWithAliasExists(key: String, aliases: String*): Boolean = !argWithAlias(key, aliases: _*).isEmpty

  override def toString() = {
    
    def formatCfg(cfg: Config): String = "%s: %s".format(cfg.origin, cfg.args.mkString(", "))
    
    "%s %s%s\nargs: %s".format(func, subfuncs.mkString(" "), allConfigs.map(formatCfg).mkString("\n  ", "\n  ", ""), args)
  }
  
}
