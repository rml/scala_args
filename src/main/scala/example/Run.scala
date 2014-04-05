package example

import rml.args.manager.DefaultRunner
import rml.args.manager.DefaultSetup
import rml.args.manager.LoggerSetup
import rml.args.manager.LogLevel._

object Run {

  RunBasic
  RunDiv
  RunFile
  RunMap
  RunString

  def main(args: Array[String]) {

    val prefix = "GG_"

    if(args.contains("-lldbg")) LoggerSetup(DEBUG)
    else  LoggerSetup(INFO)
    
    DefaultSetup(prefix)
    DefaultRunner(args, prefix)
  }

}