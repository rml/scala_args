package rml.args.register

import rml.args.arg.Arg
import rml.args.arg.ArgState
import rml.args.arg.Func
import rml.args.arg.function.FunctionOrigin
import com.typesafe.scalalogging.slf4j.LazyLogging
import rml.args.arg.FuncArg

object @@ {
  
  def apply(key: String) = RegisterBuilder(key, ArgState())
  
  def apply(key: String, desc: String) = RegisterBuilder(key, ArgState(description = desc))

  def apply(key: List[String]): FuncArg[_] = FunctionRegister(key)
  
  def update[R](key: String, func: FuncArg[R]) = FunctionRegister(key.split("\\s+").toList) = func
  
  def update[R](key: String, desc: String, func: FuncArg[R]) = FunctionRegister(key.split("\\s+").toList) = (func -- desc)
}

object @@@ {
  
  def apply(key: String): FuncArg[_] = @@(key.split("\\s+").toList)
}

object Register extends LazyLogging {

  def @@(key: String) = cmd(key)

  def key(key: String) = cmd(key)

  def cmd(key: String) = RegisterBuilder(key, ArgState())

}
