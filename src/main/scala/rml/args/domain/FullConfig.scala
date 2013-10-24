package rml.args.domain

import rml.args.exceptions.IllegalArgException

case class FullConfig(
    cmdConfig: Config,
    configs: List[Config],
    func: String, 
    subfuncs: List[String]  
) {
  
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
  val args: Map[String, List[String]] = allConfigs.map(_.args).reduce(_ ++ _)
  
  def isArg(key: String): Boolean = args.contains(key)
  
  def arg(key: String): List[String] = args(key)
  
  
  def argWithAlias(keys: String*): List[String] = {
    
    for{
      conf <- configs
      key <- keys if conf.args.contains(key)
    } return conf.args(key)
    Nil
  }

  
  def adjustPositionalArgs(pos: List[String]): FullConfig = FullConfig(cmdConfig.copy(args = cmdConfig.args + ("-" -> pos)), configs, func, subfuncs)
  
  def adjusted(subf: List[String]) = {
    if(subf.isEmpty)
      this
    else if(cmdConfig.args.contains("-"))
      throw new IllegalArgException("too many pos args") 
    else 
      FullConfig(cmdConfig, configs, func, subf) //args = fullConfig.args + ("-" -> fullConfig.subfuncs), subfuncs = List())
  }
  
}