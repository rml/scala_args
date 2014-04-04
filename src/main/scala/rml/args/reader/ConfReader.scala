package rml.args.reader

import java.io.IOException
import rml.args.domain.FunctionArgs
import scala.reflect.io.File
import rml.args.domain.Config
import rml.args.domain.FullConfig

object ConfReader {

  def apply(args: Array[String], prefix: String, conf: String): FullConfig = {
    
    apply(readCommandLineArgs(args), prefix, conf)
  }

  def apply(args: String, prefix: String, conf: String): FullConfig = {
    
    apply(readCommandLineArgs(args), prefix, conf)
  }

  def apply(cmdArgs: FunctionArgs, prefix: String, conf: String): FullConfig = {

	val envArgs: Map[String, List[String]] = if(prefix.isEmpty) Map() else readEnvironmentVars(prefix)

	val configFilePaths: List[File] = getConfigFiles(
                cmdArgs.args.get(conf), envArgs.get(conf), defaultConfFilePaths(prefix))
	    
    val fileArgs: List[Config] = if(configFilePaths.isEmpty) Nil
    										  else configFilePaths.map(f => Config(parseConfigFile(f), f.path))

    val cmdConf = Config(cmdArgs.args, "cmd")
    FullConfig(cmdConf, Config(envArgs, "env") :: fileArgs, cmdArgs.func, cmdArgs.subfuncs)
  }
  
  def readCommandLineArgs(args: Array[String]): FunctionArgs = CommandlineArgReader(args)
  
  def readCommandLineArgs(args: String): FunctionArgs = CommandlineArgReader(args)
  
  def readEnvironmentVars(prefix: String): Map[String, List[String]] = EnvironmentArgReader.prefix(prefix)

  def defaultConfFilePaths(prefix: String): List[String] = {

    val defaultConfFileName = prefix.replaceAll("_$", "").toLowerCase + ".conf"
    List("/etc/" + defaultConfFileName, System.getenv("HOME") + "/." + defaultConfFileName, System.getenv("HOME") + "/" + defaultConfFileName)
  }
  
  def getConfigFiles(cmdPaths: Option[List[String]], envPaths: Option[List[String]], defaultPaths: List[String]): List[File] = {

    val paths = cmdPaths match {
      case Some(paths) => paths
      case None => envPaths match {
        case Some(paths) => paths
        case None => defaultPaths.filter(File(_).exists)
      }
    }
    
    paths.map(File(_))
  }
  
  def parseConfigFile(confFile: File): Map[String, List[String]] = {
    
    if(!confFile.exists) throw new IOException("Config file '" + confFile.path + "' not found")
    if(confFile.isDirectory) throw new IOException("Config file '" + confFile.path + "' is a directory (expected a regular file)")
    
    FileArgReader(confFile).all()
  }
}