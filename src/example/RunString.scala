package example
import rml.args.manager.FunctionRegister
import rml.args.domain.Func
import rml.args.conversions.strings._
import rml.args.argdecorator.Env
import rml.args.domain./

object RunString {
  
  FunctionRegister("home")            = Func(Env(AString("HOME")) -- "User Dir"){println}
  FunctionRegister("foo"::"bar"::Nil) = / / "tralala" /
		  								Func(AString("bar") -- "Name of the bar"){ println }
  FunctionRegister("foo"::"baz"::Nil) = Func(AString("baz")){ println }
  FunctionRegister("upper")           = Func(JUpperString("-")){ println }
  FunctionRegister("lower")           = Func(JLowerString("-")){ println }

  val uplo = / / "Convert one string to uppercase and another to lowercase" /
  Func(LowerString("l") -- "Convert to upper", UpperString("u") -- "Convert to lower"){ (lo, up) => 
    println("Upper: " + up)
    println("Lower: " + lo)
  }
  
  FunctionRegister("uplo")            = uplo
    
}