package rml.args.register

import rml.args.arg.{ArgState, FuncArg}

case class RegisterBuilder(key: String, state: ArgState) {

  def withDescription(desc: String): RegisterBuilder =
    RegisterBuilder(key, state.copy(description = desc))

  def --(desc: String): RegisterBuilder =
    RegisterBuilder(key, state.copy(description = desc))

  def -->[R](func: FuncArg[R]): FuncArg[R] = register(func)

  def as[R](func: FuncArg[R]): FuncArg[R] = register(func)

  def run[R](func: FuncArg[R]): FuncArg[R] = register(func)

  def register[R](func: FuncArg[R]): FuncArg[R] = {

    val funcWithDescription = func -- state.description

    FunctionRegister(key.split("\\s+").toList) = funcWithDescription

    funcWithDescription
  }

}
