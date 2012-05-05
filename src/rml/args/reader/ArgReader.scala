package rml.args.reader

import scala.tools.nsc.io.File
import java.io.IOException
import rml.args.domain.FunctionArgs

object ArgReader {

  def apply(args: Array[String], prefix: String): FunctionArgs = {

    val cmdArgs: FunctionArgs = readCommandLineArgs(args)
	val envArgs: Map[String, List[String]] = if(prefix.isEmpty) Map() else readEnvironmentVars(prefix)

	val configFilePaths: List[File] = getConfigFiles(
                cmdArgs.args.get("conf"), envArgs.get("conf"), defaultConfFilePaths(prefix))
	    
    val fileArgs: Map[String, List[String]] = if(configFilePaths.isEmpty) Map()
    										  else configFilePaths.map(parseConfigFile(_)).reduceLeft(_ ++ _)

    val mergedArgs = fileArgs ++ envArgs ++ cmdArgs.args
    
    FunctionArgs(cmdArgs.func, cmdArgs.subfuncs, mergedArgs, cmdArgs.lastArg)
  }
  
  def readCommandLineArgs(args: Array[String]): FunctionArgs = CommandlineArgReader(args)
  
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
