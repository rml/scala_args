package rml.args.domain

import rml.args.arg.Arg

/**
 * Information about the called function:
 * - function name
 * - subfunction names
 * - arguments
 * - name of the last argument (this information is lost when storing the arguments in the args-Map)
 */
case class FunctionArgs(
    val func: String, 
    val subfuncs: List[String], 
    val args: Map[String, List[String]],
    val lastArg: String = "",
    val trailingDash: Boolean = false
) {
  
  def argsToString = args.toList.sortBy(_._1).map{case(k,v) => "    " + k + "\n      " + v.mkString(", ") + "\n"}.mkString
  
  def adjustLastArg(functionArgs: List[Arg[_]]): FunctionArgs = {
  
    def noLastArg = this.copy(lastArg = "")
    
    if(args.contains("-")) return noLastArg
    
    functionArgs.find(_.key == lastArg) match {
      
      case None => noLastArg
      
      case Some(arg) => {
        
          val lastList = args(arg.key)
          val trailing = arg.getUnused(lastList)
        
          if(trailing.isEmpty)
            noLastArg
          else {
            val leading = arg.getUsed(lastList)
            val adjustedArgs = args + (lastArg -> leading, "-" -> trailing)
            this.copy(lastArg = "", args = adjustedArgs)
          }
      }
    }
  }
}

