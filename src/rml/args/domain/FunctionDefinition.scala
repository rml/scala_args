package rml.args.domain

import rml.args.arg.Arg
import rml.args.argmapper._
import rml.args.reader.ArgReader
import rml.args.arg.DescriptionMethods

trait FunctionDefinition[T] extends DescriptionMethods[FunctionDefinition[T]]{

  val args = List[Arg[_]]()
  
  def getArg(argName: String) = args.find(_.key == argName)
    
  def run(args: FunctionArgs): T
  
  def apply(args: FunctionArgs): T = run(checkLastArg(args))

  def checkLastArg(args: FunctionArgs): FunctionArgs = {
  
    if(args.args.contains("-")) return args.copy(lastArg = "")
    
    getArg(args.lastArg) match {
      
      case None => args.copy(lastArg = "")
      
      case Some(lastArg) => {
        
          val lastList = args.args(lastArg.key)
          val trailing = lastArg.getUnused(lastList)
        
          if(trailing.isEmpty)
            args.copy(lastArg = "")
          else {
            val leading = lastArg.getUsed(lastList)
            val adjustedArgs = args.args + (lastArg.key -> leading, "-" -> trailing)
            args.copy(lastArg = "", args = adjustedArgs)
          }
      }
    }
  }
  
  def apply(args: Array[String], prefix: String = ""): T = {
    val functionArgs = ArgReader(args, prefix)
    val fa = if(functionArgs.args.contains("-")) functionArgs 
             else functionArgs.copy(args = functionArgs.args + ("-" -> functionArgs.subfuncs), subfuncs = List())
    apply(fa)
  }
}