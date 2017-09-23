package rml.args.generate

object FuncGenerator {

  def generate(maxArgCount: Int) {

    println("package rml.args.arg")  
    println("")  
	println("import com.typesafe.scalalogging.LazyLogging")  
	println("import rml.args.config.FullConfig")  
	println("import rml.args.manager.FunctionOrigin")  
	println("")  
	println("object Func extends LazyLogging {")  
	println("")  
	println("")  
    
    for(n <- 0 to maxArgCount){
      val r = 1 to n
      
      val functype = if(r.isEmpty) "R" else r.map(i => s"T$i").mkString("", ", ", ", R")
      val funcargsT = r.map(i => s"arg$i: Arg[T$i]").mkString(", ")
      
      val args = r.map("arg" + _) // List(arg1, arg2, ...)
      
      val arglist = args.mkString(", ") // arg1, arg2, ...
      val funcargs = if(n == 0) "" else args.map(_ + "(m)").mkString("(", ", ", ")") // (arg1(m), arg2(m), ...)
      val logargs = args.map(arg => "${" + arg + "(m)}").mkString("(", ", ", ")") // (${arg1(m)}, ${arg2(m)}, ...)
      
      if(n == 0){
        println(s"  def apply[R](func: => R)(implicit orig: FunctionOrigin) = {")
      } else {
        println(s"  def apply[${functype}]($funcargsT)(func: Function$n[$functype])(implicit orig: FunctionOrigin) = {")
      }
      
      println("")
      println(s"    val args = List($arglist)")
      println("")
      println(s"    val mapper = Mapper { (m: FullConfig) => ")
      println("")
      println( "      logger.debug(s" + "\"" + s"Called function with args: $logargs" + "\")")
      println("")
      println(s"      val res = func$funcargs")
      println("")
      println( "      logger.debug(s\"Result: ${res}\")")
      println("")
      println( "      res")
      println( "    }")
      println("")
      println( "    new FuncArg[R](args, mapper)")
      println( "  }")
      println("")
      println("")
    }
    
	println("}")  
  }
}