package rml.args.config

import rml.args.arg.Arg

/**
 * Information about the called function:
 * - function name
 * - subfunction names
 * - arguments
 * - name of the last argument (this information is lost when storing the arguments in the args-Map)
 */
case class CmdLineArgs(
    val func: String, 
    val subfuncs: List[String], 
    val args: Map[String, List[String]],
    val lastArg: String = "",
    val trailingDash: Boolean = false
) {
  
  def argsToString = args.toList.sortBy(_._1).map{case(k,v) => "    " + k + "\n      " + v.mkString(", ") + "\n"}.mkString
  
}

