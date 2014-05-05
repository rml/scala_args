package rml.args.manager

import com.typesafe.scalalogging.slf4j.{LazyLogging => Logging}

import rml.args.domain.FullConfig
import rml.args.domain.Function
import rml.args.exceptions.FunctionNotFoundException

object FunctionRunner extends Logging {

  import logger._
  
  /**
   * Try to find a function that matches the FunctionArguments and run it
   */
  def run(fullConfig: FullConfig): Any = {

    debug("fullConfig: {}", fullConfig)

    val leadingTokens = fullConfig.funcName
    
    debug("leadingTokens: {}", leadingTokens.mkString(" "))
    
    val functionName = FunctionRegister.findLongestMatching(leadingTokens) match {
      
      case Some(k) => k
      case None => throw new FunctionNotFoundException(leadingTokens)
    }
    
    debug("functionName: {}", functionName.mkString(" "))

    val function: Function[_] = FunctionRegister.get(functionName)
    
    debug("function: {}", function)
    
    val adjustedConfig: FullConfig = ConfigAdjuster(fullConfig, function, functionName)

    debug("adjustedConfig: {}", adjustedConfig)

    val result = function(adjustedConfig)    
    
    trace("result: {}", result.toString)
    
    result
  }

}