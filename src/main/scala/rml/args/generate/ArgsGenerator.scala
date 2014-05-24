package rml.args.generate

object ArgsGenerator {

  def generate(maxArgCount: Int) {

    println("package rml.args.arg")  
    println("")  
	println("import com.typesafe.scalalogging.slf4j.LazyLogging")  
	println("import rml.args.config.FullConfig")  
	println("import rml.args.manager.FunctionOrigin")  
	println("import scala.util.Try")  
    println("")  
    
    for(n <- 0 to maxArgCount){
      val r = 1 to n
      
      val functype = if(r.isEmpty) "R" else r.map(i => s"T$i").mkString("", ", ", ", R")
      val functypeNoR = if(r.isEmpty) "" else r.map(i => s"T$i").mkString("[", ", ", "]")
      val funcargsT = r.map(i => s"arg$i: Arg[T$i]").mkString(", ")
      
      val args = r.map("arg" + _) // List(arg1, arg2, ...)
      
      val arglist = args.mkString(", ") // arg1, arg2, ...
      val funcargs = if(n == 0) "" else args.map(_ + "(m).get").mkString("(", ", ", ")") // (arg1(m).get, arg2(m).get, ...)
      val logargs = args.map(arg => "${" + arg + "(m).get}").mkString("(", ", ", ")") // (${arg1(m).get}, ${arg2(m).get}, ...)

        println(s"case class Args${n}${functypeNoR}(${funcargsT}) extends LazyLogging {")
        println("")
        println(s"  val args = List(${arglist})")
        println("")

      
      if(n == 0){
        println(s"  def run[R](func: => R, argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)")
        println("")
        println(s"  def forFunction[R](func: => R, argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)")
        println("")
        println(s"  def apply[R](func: => R, argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {")
      } else {
        println(s"  def run[R](func: Function$n[$functype], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)")
        println("")
        println(s"  def forFunction[R](func: Function$n[$functype], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)")
        println("")
        println(s"  def apply[R](func: Function$n[$functype], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {")
      }
      
      println("")
      println(s"    val mapper = Mapper { (m: FullConfig) => ")
      println("")
      println("       Try {")
      println("")
      println( "        logger.debug(s" + "\"" + s"Called function with args: $logargs" + "\")")
      println(s"        val res = func$funcargs")
      println( "        logger.debug(s\"Result: ${res}\")")
      println( "        res")
      println( "      }")
      println( "    }")
      println("")
      println( "    new FuncArg[R](args, mapper, argState)")
      println( "  }")
      println("}")  
      println("")
    }


    println("")
    println("object Args {")
    println("")
    
    for(n <- 0 to maxArgCount){
      val r = 1 to n
      
      val functype = if(r.isEmpty) "R" else r.map(i => s"T$i").mkString("", ", ", ", R")
      val functypeNoR = if(r.isEmpty) "" else r.map(i => s"T$i").mkString("[", ", ", "]")
      val funcargsT = r.map(i => s"arg$i: Arg[T$i]").mkString(", ")
      
      val args = r.map("arg" + _) // List(arg1, arg2, ...)
      
      val arglist = args.mkString(", ") // arg1, arg2, ...
      val funcargs = if(n == 0) "" else args.map(_ + "(m)").mkString("(", ", ", ")") // (arg1(m), arg2(m), ...)
      val logargs = args.map(arg => "${" + arg + "(m)}").mkString("(", ", ", ")") // (${arg1(m)}, ${arg2(m)}, ...)

      println(s"  def apply${functypeNoR}(${funcargsT}): Args${n}${functypeNoR} = Args${n}(${arglist})")
      println("")
    }
    
    println("}")
    
  }
}