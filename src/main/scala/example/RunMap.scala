package example

import javax.swing.GroupLayout.Alignment
import rml.args.arg.Func
import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.conversions.map.Maps
import rml.args.conversions.map.AJavaEnum
import rml.args.conversions.map.AnEnum

object RunMap {

  implicit val origin = FunctionOrigin("RunMap")
  
  val morseMap = Map("a" -> "._", "b" -> "_...", "c" -> "_._.", "d" -> "_..", "e" -> ".", "f" -> ".._.", "g" -> "__.", "h" -> "....", "i" -> "..", "j" -> ".___", "k" -> "_._", "l" -> "._..", "m" -> "__", "n" -> "_.", "o" -> "___", "p" -> ".__.", "q" -> "__._", "r" -> "._.", "s" -> "...", "t" -> "_", "u" -> ".._", "v" -> "..._", "w" -> ".__", "x" -> "_.._", "y" -> "_.__", "z" -> "__..", "0" -> "-----", "1" -> ".----", "2" -> "..---", "3" -> "...--", "4" -> "....-", "5" -> ".....", "6" -> "-....", "7" -> "--...", "8" -> "---..", "9" -> "----.")
  val unmorseMap = morseMap.map{case(k,v) => (v,k)}
  
  val morse = Maps("m", morseMap)
  val unmorse = Maps("u", unmorseMap)
  
  val jenumarg = AJavaEnum("en", classOf[Alignment])

  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }
  
  val weekday = AnEnum("wd", WeekDay) -- "Weekday"
    
  FunctionRegister("jenum")            = Func(jenumarg){println}
  FunctionRegister("weekday")          = Func(weekday){println}
  FunctionRegister("morse")            = Func(morse){ s => println(s.mkString(" "))}
  FunctionRegister("unmorse")          = Func(unmorse){ s => println(s.mkString(""))}
  
}