package rml.args.manager

import rml.args.domain.FullConfig
import com.typesafe.scalalogging.slf4j.Logging
import rml.args.exceptions.FunctionNotFoundException
import rml.args.exceptions.IllegalArgException
import rml.args.domain.Function

object FunctionRunner extends Logging {

  import logger._
  
  /**
   * Try to find a function that matches the FunctionArguments and run it
   */
  def run(fullConfig: FullConfig): Any = {

    debug("{}", fullConfig)

    val leadingTokens = fullConfig.funcName
    
    val functionName = FunctionRegister.findLongestMatching(leadingTokens) match {
      
      case Some(k) => k
      case None => throw new FunctionNotFoundException(leadingTokens)
    }

    val function: Function[_] = FunctionRegister.get(functionName)
    
    val adjustedConfig: FullConfig = ConfigAdjuster(fullConfig, function, functionName)
    
    function(adjustedConfig)    
  }

}