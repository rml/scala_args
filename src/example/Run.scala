
import java.io.IOException
import scala.tools.nsc.io.File
import rml.args.manager.FunctionRegister
import rml.args.domain.Func
import rml.args.conversions.strings.AString
import rml.args.conversions.basic.AnInt
import rml.args.conversions.files.PFile
import rml.args.conversions.basic.PInt
import rml.args.conversions.files.PFile
import rml.args.conversions.basic.Booleans
import rml.args.reader.ArgReader
import rml.args.conversions.dates.Today
import rml.args.conversions.dates.AnIsoDate
import rml.args.arg.Env
import rml.args.conversions.files.AFile
import rml.args.arg.FixArg
import rml.args.arg.Flag
import rml.args.conversions.strings.PUpperString
import rml.args.conversions.strings.PLowerString
import rml.args.conversions.strings.LowerString
import rml.args.conversions.strings.UpperString
import rml.args.conversions.basic.AFloat
import rml.args.arg.WithAlias

object Run {

  FunctionRegister("foo"::"bar"::Nil) = Func(AString("bar")){ println }
  FunctionRegister("foo"::"baz"::Nil) = Func(AString("baz")){ println }
  FunctionRegister("remote"::"add"::Nil) = Func(Flag("v"), AString("url")){ (verbose, url) => if(verbose) println(url) }
  FunctionRegister("generate")        = Func(AnInt("max")){ Func.create }
  FunctionRegister("cat")             = Func(PFile(1)){ f => f.lines.foreach(println) }
  FunctionRegister("sq")              = Func(AnInt("x")){ x => println(x * x)}
  FunctionRegister("quad")            = FunctionRegister("sq")
  FunctionRegister("*")               = Func(PInt(1)){ x => println(x * x)}
  FunctionRegister("help")            = FunctionRegister.help
  FunctionRegister("")                = FunctionRegister("help")
  FunctionRegister("env")             = Func{ println(System.getenv()) }
  FunctionRegister("bools")           = Func(Booleans("-")){ println }
  FunctionRegister("date")            = Func(AnIsoDate("d")){ println }
  FunctionRegister("today")           = Func(Today()){ println }
  FunctionRegister("yes")             = Func(Flag("y"), Flag("z")){ (a, b) => println(a); println(b) }
  FunctionRegister("upper")           = Func(PUpperString(1)){ println }
  FunctionRegister("lower")           = Func(PLowerString(1)){ println }
  FunctionRegister("def")             = Func(AFloat("a").withDefault(AFloat("b"))){ println }
  FunctionRegister("al")              = Func(WithAlias(AFloat("a"), "c", "-anumber")){ println }
  FunctionRegister("al2")             = Func(AFloat("a").withAlias("c", "-anumber").withDefault(FixArg(78f))){ println }
  
  val uplo = Func(LowerString("l"), UpperString("u")){ (lo, up) => 
    println("Upper: " + up)
    println("Lower: " + lo)
  }
  
  FunctionRegister("uplo")            = uplo
    
  def main(args: Array[String]) {
    val functionArguments = ArgReader(args, "GG_")
    FunctionRegister.run(functionArguments)
  }

}