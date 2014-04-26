package rml.args.manager

import rml.args.domain.FullConfig
import rml.args.domain.Config
import rml.args.arg.Arg
import rml.args.domain.FunctionArgs
import rml.args.domain.Function
import rml.args.exceptions.IllegalArgException

object ConfigAdjuster {

  def apply(fullConfig: FullConfig, function: Function[_], functionName: List[String]): FullConfig = {
    
    val leadingPositionalArgs = fullConfig.funcName.drop(functionName.length)
    
    val cmdConfig: Config = if(leadingPositionalArgs.isEmpty){
      fullConfig.cmdConfig
    } else if(!fullConfig.cmdConfig.contains("-")){
      val argsWithPositional: Map[String, List[String]] = fullConfig.cmdConfig.args + ("-" -> leadingPositionalArgs)
      fullConfig.cmdConfig.copy(args = argsWithPositional)
    } else {
      val msg = "Found leading positional args ([%s] %s) and explicit positional args (-- %s)"
        .format(functionName.mkString(" "), leadingPositionalArgs.mkString(" "), fullConfig.cmdConfig("-").mkString(" "))
        throw new IllegalArgException(msg)
    }
    
    fullConfig.copy(cmdConfig = adjust(cmdConfig, function))
  }
  
  def adjust(cmdConfig: Config, function: Function[_]): Config = {

    val cmdLineArgs = cmdConfig.args
    val funcArgs = function.inputArgs
    
    val trailingArgs = for{ (k,v) <- cmdLineArgs.toList
      arg <- function.inputArgOption(k)
      trailing = arg.getUnused(v)
      if !trailing.isEmpty
    } yield (k, trailing)

    val explicitPositionalArgs = cmdLineArgs.contains("-")
    val legalState = trailingArgs.isEmpty || (trailingArgs.tail.isEmpty && !explicitPositionalArgs)

    if(!explicitPositionalArgs && trailingArgs.isEmpty){
      return cmdConfig
    }
    
    if(explicitPositionalArgs){
      
      if(trailingArgs.isEmpty){
        return cmdConfig
        
      } else {

        val msg = "When explicit positional args are set (%s), trailing args are not allowed (%s)"
          .format(cmdLineArgs("-").mkString(" "), trailingArgs.map{case(k,v) => s"$k: ${v.mkString(" ")}"}.mkString(", "))
          throw new IllegalArgException(msg)
      }
    }
    
    if(!trailingArgs.tail.isEmpty){
      val msg = "Too many trailing args (%s)".format(trailingArgs.map{case(k,v) => s"$k: ${v.mkString(" ")}"}.mkString(", "))
      throw new IllegalArgException(msg)      
    }
    
    val (adjustArgName, argList) = trailingArgs.head
    val adjustArg = funcArgs.find(_.key == adjustArgName).get

    val adjustedArgs = cmdConfig.args + (adjustArgName -> adjustArg.getUsed(argList), "-" -> adjustArg.getUnused(argList))

    cmdConfig.copy(args = adjustedArgs)
  }
  
}