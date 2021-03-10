package example

import rml.args.arg.function.FunctionOrigin
import rml.args.arg.{Func, InputArg}
import rml.args.conversions.map.{AJavaEnum, AnEnum, Maps}
import rml.args.register.@@

import javax.swing.GroupLayout.Alignment

object RunMap {

  implicit val origin: FunctionOrigin = FunctionOrigin("RunMap")

  val morseMap = Map(
    "a" -> "._",
    "b" -> "_...",
    "c" -> "_._.",
    "d" -> "_..",
    "e" -> ".",
    "f" -> ".._.",
    "g" -> "__.",
    "h" -> "....",
    "i" -> "..",
    "j" -> ".___",
    "k" -> "_._",
    "l" -> "._..",
    "m" -> "__",
    "n" -> "_.",
    "o" -> "___",
    "p" -> ".__.",
    "q" -> "__._",
    "r" -> "._.",
    "s" -> "...",
    "t" -> "_",
    "u" -> ".._",
    "v" -> "..._",
    "w" -> ".__",
    "x" -> "_.._",
    "y" -> "_.__",
    "z" -> "__..",
    "0" -> "-----",
    "1" -> ".----",
    "2" -> "..---",
    "3" -> "...--",
    "4" -> "....-",
    "5" -> ".....",
    "6" -> "-....",
    "7" -> "--...",
    "8" -> "---..",
    "9" -> "----."
  )
  val unmorseMap: Map[String, String] = morseMap.map { case (k, v) => (v, k) }

  val morse: InputArg[List[String]] = Maps("m", morseMap)
  val unmorse: InputArg[List[String]] = Maps("u", unmorseMap)

  val jenumarg: InputArg[Alignment] = AJavaEnum("en", classOf[Alignment])

  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }

  val weekday: InputArg[WeekDay.Value] = AnEnum("wd", WeekDay) -- "Weekday"

  @@("jenum") -->
    Func(jenumarg) { println }

  @@("weekday") -->
    Func(weekday) { println }

  @@("morse") -->
    Func(morse) { s => println(s.mkString(" ")) }

  @@("unmorse") -->
    Func(unmorse) { s => println(s.mkString("")) }

}
