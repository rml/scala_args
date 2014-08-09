package example

import rml.args.arg.{/ => /}
import rml.args.arg.Func
import rml.args.arg.decorator.Env
import rml.args.arg.function.FunctionOrigin
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.JLowerString
import rml.args.conversions.strings.JUpperString
import rml.args.conversions.strings.LowerStrings
import rml.args.conversions.strings.UpperStrings
import rml.args.register.@@

object RunString {
  
  implicit val origin = FunctionOrigin("RunString")
  
  @@("home") -->
  Func(Env(AString("HOME")) -- "User Dir"){println}
  
  @@("foo bar", "tralala") -->
  Func(AString("bar") -- "Name of the bar"){ println }
  
  @@("foo baz") -->
  Func(AString("baz")){ println }
  
  @@("foo") -->
  Func(AString("barbaz")){ println }
  
  @@("upper") = Func(JUpperString("-")){ println }
  @@("lower") = Func(JLowerString("-")){ println }

  val uplo = / / "Convert one string to uppercase and another to lowercase" /
  Func(LowerStrings("l") -- "Convert to upper", UpperStrings("u") -- "Convert to lower"){ (lo, up) => 
    println("Upper: " + up)
    println("Lower: " + lo)
  }
  
  @@("uplo")            = uplo
    
}