package rml.args.generate

case class ArgumentGeneratorHelper(argCount: Int) {

  val r = 1 to argCount
  val functype: String =
    if (r.isEmpty) "R" else r.map(i => s"T$i").mkString("", ", ", ", R")
  val funcargsT: String = r.map(i => s"arg$i: Arg[T$i]").mkString(", ")

  val args: Seq[String] = r.map("arg" + _) // List(arg1, arg2, ...)

  val arglist: String = args.mkString(", ") // arg1, arg2, ...
  val funcargs: String =
    if (argCount == 0) ""
    else args.map(_ + "(m)").mkString("(", ", ", ")") // (arg1(m), arg2(m), ...)
  val logargs: String = args
    .map(arg => "${" + arg + "(m)}")
    .mkString("(", ", ", ")") // (${arg1(m)}, ${arg2(m)}, ...)

  val functionT: String = "Function" + argCount + "[" + functype + "]"
}
