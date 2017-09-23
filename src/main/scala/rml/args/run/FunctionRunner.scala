package rml.args.run

import scala.util.Try
import com.typesafe.scalalogging.{LazyLogging => Logging}
import rml.args.arg.FuncArg
import rml.args.config.ConfigAdjuster
import rml.args.config.FullConfig
import rml.args.exceptions.FunctionNotFoundException
import rml.args.register.FunctionRegister
import scala.util.Failure

object FunctionRunner extends Logging {

  import logger._
  
  /**
   * Try to find a function that matches the FunctionArguments and run it
   */
  def run(fullConfig: FullConfig): Try[Any] = {

    debug("fullConfig: {}", fullConfig)

    val leadingTokens = fullConfig.funcName
    
    debug("leadingTokens: {}", leadingTokens.mkString(" "))
    
    val functionName = FunctionRegister.findLongestMatching(leadingTokens) match {
      
      case Some(k) => k
      case None => return Failure(new FunctionNotFoundException(leadingTokens))
    }
    
    debug("functionName: {}", functionName.mkString(" "))

    val function: FuncArg[_] = FunctionRegister.get(functionName)
    
    debug("function: {}", function)
    
    val adjustedConfig: FullConfig = ConfigAdjuster(fullConfig, function, functionName)

    debug("adjustedConfig: {}", adjustedConfig)

    val result = function(adjustedConfig)    
    
    trace("result: {}", result.toString)
    
    result
  }

}