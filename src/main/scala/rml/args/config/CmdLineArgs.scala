package rml.args.config

/** Information about the called function:
  * - function name
  * - subfunction names
  * - arguments
  * - name of the last argument (this information is lost when storing the arguments in the args-Map)
  */
case class CmdLineArgs(
    func: String,
    subfuncs: List[String],
    args: Map[String, List[String]],
    lastArg: String = "",
    trailingDash: Boolean = false
) {

  def argsToString: String = args.toList
    .sortBy(_._1)
    .map { case (k, v) => "    " + k + "\n      " + v.mkString(", ") + "\n" }
    .mkString

}
