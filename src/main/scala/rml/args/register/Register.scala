package rml.args.register

import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.{ArgState, FuncArg}

object @@ {

  def apply(key: String): RegisterBuilder = RegisterBuilder(key, ArgState())

  def apply(key: String, desc: String): RegisterBuilder =
    RegisterBuilder(key, ArgState(description = desc))

  def apply(key: List[String]): FuncArg[_] = FunctionRegister(key)

  def update[R](key: String, func: FuncArg[R]): Unit = FunctionRegister(
    key.split("\\s+").toList
  ) = func

  def update[R](key: String, desc: String, func: FuncArg[R]): Unit =
    FunctionRegister(
      key.split("\\s+").toList
    ) = func -- desc
}

object @@@ {

  def apply(key: String): FuncArg[_] = @@(key.split("\\s+").toList)
}

object Register extends LazyLogging {

  def @@(key: String): RegisterBuilder = cmd(key)

  def key(key: String): RegisterBuilder = cmd(key)

  def cmd(key: String): RegisterBuilder = RegisterBuilder(key, ArgState())

}
