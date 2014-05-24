package rml.args.generate

object RegisterGenerator {

  def generate(maxArgCount: Int) {

    println("package rml.args.register")  
    println("")  
    println("import rml.args.arg.Arg")  
    println("import rml.args.arg.ArgState")  
    println("import rml.args.arg.Func")  
    println("import rml.args.manager.FunctionOrigin")  
    println("import rml.args.manager.FunctionRegister")  
    println("import com.typesafe.scalalogging.slf4j.LazyLogging")
    println("")
	println("object Register extends LazyLogging {")  
	println("")  
	println("  def name(key: String) = cmd(key)")
	println("")
	println("  def key(key: String) = cmd(key)")
	println("")
	println("  def cmd(key: String) = RegisterBuilder(key, ArgState())")
	println("")
    
    for(n <- 0 to maxArgCount){
      
      val helper = ArgumentGeneratorHelper(n)
      
      import helper._
      
      printf ("  def apply[%s](key: String)(desc: String)(%s)(func: %s)(implicit orig: FunctionOrigin): Unit = {\n", functype, funcargsT, functionT)
      println("")
      printf ("    FunctionRegister(key) = Func(%s)(func) -- desc\n", arglist)
      println("  }")
      println("")
      
      printf ("  def withoutDescription[%s](key: String)(%s)(func: %s)(implicit orig: FunctionOrigin): Unit = {\n", functype, funcargsT, functionT)
      println("")
      printf ("    FunctionRegister(key) = Func(%s)(func)\n", arglist)
      println("  }")
      println("")
    }
    
    println("}")
  }
  
}