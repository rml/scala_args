package example

import rml.args.manager.FunctionRegister
import rml.args.domain.Func
import rml.args.conversions.basic._
import rml.args.arg._
import rml.args.argdecorator.WithAlias
import rml.args.domain.FunctionDefinition
import rml.args.domain./
import rml.args.argdecorator.Env
import rml.args.argdecorator.WithFlagAlias
import rml.args.conversions.basic.Floats0
import rml.args.conversions.basic.Booleans0
import rml.args.conversions.basic.Longs0
import rml.args.conversions.basic.Ints0
import rml.args.conversions.basic.BigDecimals0
import rml.args.conversions.basic.AnInt
import rml.args.conversions.basic.Bytes0
import rml.args.conversions.basic.AFloat
import rml.args.conversions.basic.Shorts0
import rml.args.conversions.basic.PInt
import rml.args.conversions.basic.Doubles0
import rml.args.argdecorator.Opt
import rml.args.conversions.strings.PString

object RunBasic {
    
  FunctionRegister("sq")              = / / "Calculate square" /
                                        Func(AnInt("x") ~ "-" -> 10 -- "Number"
                                            ){ x => println(x * x)}

  FunctionRegister("quad")            = / / "Alias for 'sq'" -- FunctionRegister("sq")
  
  FunctionRegister("*")               = / / "Multiply" /
  										Func(PInt(1) -- "Number"){ x => println(x * x)}
  
  FunctionRegister("yes")             = / / "Show Flags y and z" /
		  								Func(Flag("y") -- "Flag y", Flag("z") -- "Flag z"){ (a, b) => println(a); println(b) }
  
  FunctionRegister("al")              = / / "Function with aliases" /
		  								Func(AFloat("a") ~ "c" ~ "-anumber" -- "Float"){ println }
  
  FunctionRegister("int")             = Func(Ints0("-")){println}
  FunctionRegister("long")            = Func(Longs0("-")){println}
  FunctionRegister("bd")              = Func(BigDecimals0("-")){println}
  FunctionRegister("bool")            = Func(Booleans0("-")){println}
  FunctionRegister("byte")            = Func(Bytes0("-")){println}
  FunctionRegister("short")           = Func(Shorts0("-")){println}
  FunctionRegister("double")          = Func(Doubles0("-")){println}
  FunctionRegister("float")           = / / "List of floats" /
                                        Func(Floats0("-")){println}
  
  FunctionRegister("pos")             = / / "Optional positional arguments" /
                                        Func(Opt(PString(1)), Opt(PString(2))){ (a, b) => println(a + " " + b)}

}