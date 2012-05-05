package rml.args.domain

import rml.args.arg.Arg
import rml.args.arg.ArgMapper
import rml.args.reader.ArgReader

trait FunctionDefinition[T] {

  val args = List[Arg[_]]()
  
  val description: String = ""

  def getArg(argName: String) = args.find(_.key == argName)
    
  def run(args: FunctionArgs): T
  
  def apply(args: FunctionArgs): T = run(checkLastArg(args))

  def checkLastArg(args: FunctionArgs): FunctionArgs = {
  
    if(args.args.contains("-")) return args.copy(lastArg = "")
    
    getArg(args.lastArg) match {
      
      case None => args.copy(lastArg = "")
      
      case Some(lastArg) => lastArg match {
        
        case a: ArgMapper[_] =>

          val lastList = args.args(a.key)
          val trailing = a.getUnused(lastList)
        
          if(trailing.isEmpty)
            args.copy(lastArg = "")
          else {
            val leading = a.getUsed(lastList)
            val adjustedArgs = args.args + (a.key -> leading, "-" -> trailing)
            args.copy(lastArg = "", args = adjustedArgs)
          }
        case _ => args.copy(lastArg = "")
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