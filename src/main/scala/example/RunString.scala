package example

import rml.args.arg.Func
import rml.args.arg.decorator.Env
import rml.args.arg.{/ => /}
import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.JUpperString
import rml.args.conversions.strings.LowerStrings
import rml.args.conversions.strings.JLowerString
import rml.args.conversions.strings.UpperStrings

object RunString {
  
  implicit val origin = FunctionOrigin("RunString")
  
  FunctionRegister("home")            = Func(Env(AString("HOME")) -- "User Dir"){println}
  FunctionRegister("foo"::"bar"::Nil) = / / "tralala" /
		  								Func(AString("bar") -- "Name of the bar"){ println }
  FunctionRegister("foo"::"baz"::Nil) = Func(AString("baz")){ println }
  FunctionRegister("foo") = Func(AString("barbaz")){ println }
  FunctionRegister("upper")           = Func(JUpperString("-")){ println }
  FunctionRegister("lower")           = Func(JLowerString("-")){ println }

  val uplo = / / "Convert one string to uppercase and another to lowercase" /
  Func(LowerStrings("l") -- "Convert to upper", UpperStrings("u") -- "Convert to lower"){ (lo, up) => 
    println("Upper: " + up)
    println("Lower: " + lo)
  }
  
  FunctionRegister("uplo")            = uplo
    
}