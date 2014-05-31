package rml.args.config.reader

import java.io.IOException
import java.io.File
import scala.reflect.io.Path.string2path
import com.typesafe.scalalogging.slf4j.LazyLogging
import rml.args.config.CmdLineArgs
import rml.args.config.Config
import rml.args.config.FullConfig

object ConfReader extends LazyLogging {

  def apply(args: Array[String], prefix: String, conf: String): FullConfig = {
    
    apply(readCommandLineArgs(args), prefix, conf)
  }

  def apply(args: String, prefix: String, conf: String): FullConfig = {
    
    apply(readCommandLineArgs(args), prefix, conf)
  }

  def apply(cmdArgs: CmdLineArgs, prefix: String, conf: String): FullConfig = {

	val envArgs: Map[String, List[String]] = if(prefix.isEmpty) Map() else readEnvironmentVars(prefix)

	logger.debug(s"envArgs: $envArgs")
	
	val sysProps: Map[String, List[String]] = if(prefix.isEmpty) Map() else readSystemProperties(prefix)

	logger.debug(s"sysProps: $sysProps")
	
	val configFilePaths: List[File] = getConfigFiles(
                cmdArgs.args.get(conf), envArgs.get(conf), defaultConfFilePaths(prefix))
	    
	logger.debug(s"configFilePaths: $configFilePaths")
	
    val fileArgs: List[Config] = if(configFilePaths.isEmpty) Nil
    										  else configFilePaths.map(f => Config(parseConfigFile(f), f.getAbsolutePath()))

	logger.debug(s"fileArgs: $fileArgs")
	
    val cmdConf = Config(cmdArgs.args, "cmd")
    
	logger.debug(s"cmdConf: $cmdConf")
	
    FullConfig(cmdConf, Config(sysProps, "sysProps") :: Config(envArgs, "env") :: fileArgs, cmdArgs.func, cmdArgs.subfuncs)
  }
  
  def readCommandLineArgs(args: Array[String]): CmdLineArgs = CommandlineArgReader(args)
  
  def readCommandLineArgs(args: String): CmdLineArgs = CommandlineArgReader(args)
  
  def readEnvironmentVars(prefix: String): Map[String, List[String]] = EnvironmentArgReader.prefix(prefix)

  def readSystemProperties(prefix: String): Map[String, List[String]] = SystemPropertiesArgReader.prefix(prefix.toLowerCase.replaceAll("_", "."))

  def defaultConfFilePaths(prefix: String): List[String] = {

    val defaultConfFileName = prefix.replaceAll("_$", "").toLowerCase + ".conf"
    List("/etc/" + defaultConfFileName, System.getenv("HOME") + "/." + defaultConfFileName, System.getenv("HOME") + "/" + defaultConfFileName)
  }
  
  def getConfigFiles(cmdPaths: Option[List[String]], envPaths: Option[List[String]], defaultPaths: List[String]): List[File] = {

    val paths = cmdPaths match {
      case Some(paths) => paths
      case None => envPaths match {
        case Some(paths) => paths
        case None => defaultPaths.filter(new File(_).exists)
      }
    }
    
    paths.map(new File(_))
  }
  
  def parseConfigFile(confFile: File): Map[String, List[String]] = {
    
    if(!confFile.exists) throw new IOException("Config file '" + confFile.getAbsolutePath() + "' not found")
    if(confFile.isDirectory) throw new IOException("Config file '" + confFile.getAbsolutePath() + "' is a directory (expected a regular file)")
    
    FileArgReader(confFile).all()
  }
}