package example

import rml.args.arg.decorator.Opt
import rml.args.arg.function.FunctionOrigin
import rml.args.arg.special.Flag
import rml.args.arg.{Func, InputArg}
import rml.args.conversions.basic._
import rml.args.conversions.strings.PString
import rml.args.register.{@@, @@@, FunctionRegister}

object RunBasic {

  implicit val origin: FunctionOrigin = FunctionOrigin("RunBasic")

  val x: InputArg[Int] = AnInt("x")

  @@("sq", "Calculate square") -->
    Func(AnInt("x") ~ "-" -> 10 -- "Number") { x =>
      println(x * x)
    }

  @@("sq2", "Calculate square") -->
    Func(x) { x =>
      println(x * x)
    }

  @@("quad", "Alias for 'sq'") = FunctionRegister("sq")

  @@("quad2", "Alias 2 for 'sq'") = @@@("sq")

  @@("*", "Multiply") -->
    Func(PInt(1) -- "Number") { x => println(x * x) }

  @@("yes", "Show Flags y and z") -->
    Func(Flag("y") -- "Flag y", Flag("z") -- "Flag z") { (a, b) =>
      println(a); println(b)
    }

  @@("al", "Function with aliases") -->
    Func(AFloat("a") ~ "c" ~ "-anumber" -- "Float") { println }

  @@("int") -->
    Func(Ints0("i")) { println }

  @@("long") -->
    Func(Longs0("-")) { println }

  @@("bd") -->
    Func(BigDecimals0("-")) { println }

  @@("bool") -->
    Func(Booleans0("-")) { println }

  @@("byte") -->
    Func(Bytes0("-")) { println }

  @@("short") -->
    Func(Shorts0("-")) { println }

  @@("double") -->
    Func(Doubles0("-")) { println }

  @@("float", "List of floats") -->
    Func(Floats0("-")) { println }

  @@("pos", "Tryal positional arguments") -->
    Func(Opt(PString(1)) -- "p1", Opt(PString(2)) -- "p2") { (a, b) =>
      println(s"$a $b")
    }

  @@("pos2", "Positional arguments")
  Func(PString(1) -- "p1", PString(2) -- "p2") { (a, b) =>
    println(a + " " + b)
  }

}
