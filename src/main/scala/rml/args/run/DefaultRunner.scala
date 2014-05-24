package rml.args.run

import scala.util.Failure
import scala.util.Success
import com.typesafe.scalalogging.slf4j.{LazyLogging => Logging}
import rml.args.config.FullConfig
import rml.args.config.reader.ConfReader
import rml.args.exceptions.FunctionNotFoundException
import rml.args.exceptions.IllegalArgException

object DefaultRunner extends Logging {
  
  import logger._

  def apply(args: Array[String], prefix: String): Unit = run(ConfReader(args, prefix, "conf"), prefix)
    
  def apply(args: String, prefix: String): Unit = run(ConfReader(args, prefix, "conf"), prefix)
    
  def run(fullConfig: FullConfig, prefix: String): Any = {
    
    val showStacktrace = fullConfig.args.contains("stack")
    
    def printMessageAndStacktrace(e: Throwable, showStacktrace: Boolean) = {
      
      error(e.getMessage)
        
      if(showStacktrace){
        e.printStackTrace()
      }
    }
    
    FunctionRunner.run(fullConfig) match {
      case Success(res)                             => res
      case Failure(iae: IllegalArgException)        => printMessageAndStacktrace(iae, showStacktrace)
      case Failure(fnfe: FunctionNotFoundException) => error("Function '" + fnfe.function.head + "' not found\nDid you mean one of these?")
      case Failure(e)                               => printMessageAndStacktrace(e, showStacktrace)
    }
    
  }
}