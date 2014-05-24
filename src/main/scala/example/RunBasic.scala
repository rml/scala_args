package example

import rml.args.arg.{/ => /}
import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.arg.Func
import rml.args.conversions.basic.AnInt
import rml.args.conversions.basic.PInt
import rml.args.arg.special.Flag
import rml.args.conversions.basic.AFloat
import rml.args.conversions.basic.Ints0
import rml.args.conversions.basic.Longs0
import rml.args.conversions.basic.BigDecimals0
import rml.args.conversions.basic.Booleans0
import rml.args.conversions.basic.Bytes0
import rml.args.conversions.basic.Shorts0
import rml.args.conversions.basic.Doubles0
import rml.args.conversions.basic.Floats0
import rml.args.arg.decorator.Opt
import rml.args.conversions.strings.PString

object RunBasic {
  
  implicit val origin = FunctionOrigin("RunBasic")

//  Register("sq")(
//      
//      "Calculate square"
//      
//  )(
//      AnInt("x") ~ "-" -> 10 -- "Number"
//  ){ x => println(x * x)}
  
  val x = AnInt("x")
  
  FunctionRegister("sq")              = / / "Calculate square" /
                                        Func(AnInt("x") ~ "-" -> 10 -- "Number"){ x =>
                                          println(x * x)
      }

  FunctionRegister("sq2")              = / / "Calculate square" /
                                        Func(x){ x =>
                                          println(x * x)
      }

  FunctionRegister("quad")            = / / "Alias for 'sq'" -- FunctionRegister("sq")
  
  FunctionRegister("*")               = / / "Multiply" /
  										Func(PInt(1) -- "Number"){ x => println(x * x)}
  
  FunctionRegister("yes")             = / / "Show Flags y and z" /
		  								Func(Flag("y") -- "Flag y", Flag("z") -- "Flag z"){ (a, b) => println(a); println(b) }
  
  FunctionRegister("al")              = / / "Function with aliases" /
		  								Func(AFloat("a") ~ "c" ~ "-anumber" -- "Float"){ println }
  
  FunctionRegister("int")             = Func(Ints0("i")){println}
  FunctionRegister("long")            = Func(Longs0("-")){println}
  FunctionRegister("bd")              = Func(BigDecimals0("-")){println}
  FunctionRegister("bool")            = Func(Booleans0("-")){println}
  FunctionRegister("byte")            = Func(Bytes0("-")){println}
  FunctionRegister("short")           = Func(Shorts0("-")){println}
  FunctionRegister("double")          = Func(Doubles0("-")){println}
  FunctionRegister("float")           = / / "List of floats" /
                                        Func(Floats0("-")){println}
  
  FunctionRegister("pos")             = / / "Tryal positional arguments" /
                                        Func(Opt(PString(1)) -- "p1", Opt(PString(2)) -- "p2"){ (a, b) => println(a + " " + b)}

  FunctionRegister("pos2")             = / / "Positional arguments" /
                                        Func(PString(1) -- "p1", PString(2) -- "p2"){ (a, b) => println(a + " " + b)}

}