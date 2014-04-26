package rml.args.manager

import com.typesafe.scalalogging.slf4j.Logging

import rml.args.argdecorator.Opt
import rml.args.domain.FullConfig
import rml.args.exceptions.FunctionNotFoundException
import rml.args.exceptions.IllegalArgException
import rml.args.reader.ConfReader

object DefaultRunner extends Logging {
  
  import logger._

  def apply(args: Array[String], prefix: String): Unit = run(ConfReader(args, prefix, "conf"), prefix)
    
  def apply(args: String, prefix: String): Unit = run(ConfReader(args, prefix, "conf"), prefix)
    
  def run(fullConfig: FullConfig, prefix: String): Unit = {
    
    try {
//      Opt(AnEnum("loglevel", LogLevel)).apply(fullConfig).foreach(LoggerSetup.setLoglevel)
      
      FunctionRunner.run(fullConfig)
      
    } catch {
      
      case iae: IllegalArgException => 
        
        error(iae.getMessage)
        
        if(fullConfig.args.contains("-stack")){
          iae.printStackTrace()
        }
      
      case fnfe: FunctionNotFoundException => 
        
        error("Function '" + fnfe.function.head + "' not found\nDid you mean one of these?")
    }
  }
}