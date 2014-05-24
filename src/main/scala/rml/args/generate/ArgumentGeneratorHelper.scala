package rml.args.generate

case class ArgumentGeneratorHelper(argCount: Int) {

  val r = 1 to argCount
  val functype = if(r.isEmpty) "R" else r.map(i => s"T$i").mkString("", ", ", ", R")
  val funcargsT = r.map(i => s"arg$i: Arg[T$i]").mkString(", ")
  
  val args = r.map("arg" + _) // List(arg1, arg2, ...)
  
  val arglist = args.mkString(", ") // arg1, arg2, ...
  val funcargs = if(argCount == 0) "" else args.map(_ + "(m)").mkString("(", ", ", ")") // (arg1(m), arg2(m), ...)
  val logargs = args.map(arg => "${" + arg + "(m)}").mkString("(", ", ", ")") // (${arg1(m)}, ${arg2(m)}, ...)
  
  val functionT = "Function" + argCount + "[" + functype + "]"
}