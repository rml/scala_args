package rml.args.arg

import com.typesafe.scalalogging.LazyLogging
import rml.args.config.FullConfig
import rml.args.arg.function.FunctionOrigin
import scala.util.Try

case class Args0() extends LazyLogging {

  val args = List()

  def run[R](func: => R, argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: => R, argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: => R, argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: ()")
        val res = func
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args1[T1](arg1: Arg[T1]) extends LazyLogging {

  val args = List(arg1)

  def run[R](func: Function1[T1, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function1[T1, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function1[T1, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get})")
        val res = func(arg1(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args2[T1, T2](arg1: Arg[T1], arg2: Arg[T2]) extends LazyLogging {

  val args = List(arg1, arg2)

  def run[R](func: Function2[T1, T2, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function2[T1, T2, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function2[T1, T2, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get})")
        val res = func(arg1(m).get, arg2(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args3[T1, T2, T3](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3]) extends LazyLogging {

  val args = List(arg1, arg2, arg3)

  def run[R](func: Function3[T1, T2, T3, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function3[T1, T2, T3, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function3[T1, T2, T3, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args4[T1, T2, T3, T4](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4)

  def run[R](func: Function4[T1, T2, T3, T4, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function4[T1, T2, T3, T4, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function4[T1, T2, T3, T4, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args5[T1, T2, T3, T4, T5](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5)

  def run[R](func: Function5[T1, T2, T3, T4, T5, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function5[T1, T2, T3, T4, T5, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function5[T1, T2, T3, T4, T5, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args6[T1, T2, T3, T4, T5, T6](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6)

  def run[R](func: Function6[T1, T2, T3, T4, T5, T6, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function6[T1, T2, T3, T4, T5, T6, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function6[T1, T2, T3, T4, T5, T6, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args7[T1, T2, T3, T4, T5, T6, T7](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7)

  def run[R](func: Function7[T1, T2, T3, T4, T5, T6, T7, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function7[T1, T2, T3, T4, T5, T6, T7, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function7[T1, T2, T3, T4, T5, T6, T7, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args8[T1, T2, T3, T4, T5, T6, T7, T8](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)

  def run[R](func: Function8[T1, T2, T3, T4, T5, T6, T7, T8, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function8[T1, T2, T3, T4, T5, T6, T7, T8, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function8[T1, T2, T3, T4, T5, T6, T7, T8, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args9[T1, T2, T3, T4, T5, T6, T7, T8, T9](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)

  def run[R](func: Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)

  def run[R](func: Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11)

  def run[R](func: Function11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12)

  def run[R](func: Function12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13)

  def run[R](func: Function13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14)

  def run[R](func: Function14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15)

  def run[R](func: Function15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get}, ${arg15(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get, arg15(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16)

  def run[R](func: Function16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get}, ${arg15(m).get}, ${arg16(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get, arg15(m).get, arg16(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17)

  def run[R](func: Function17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get}, ${arg15(m).get}, ${arg16(m).get}, ${arg17(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get, arg15(m).get, arg16(m).get, arg17(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18)

  def run[R](func: Function18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get}, ${arg15(m).get}, ${arg16(m).get}, ${arg17(m).get}, ${arg18(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get, arg15(m).get, arg16(m).get, arg17(m).get, arg18(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19)

  def run[R](func: Function19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get}, ${arg15(m).get}, ${arg16(m).get}, ${arg17(m).get}, ${arg18(m).get}, ${arg19(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get, arg15(m).get, arg16(m).get, arg17(m).get, arg18(m).get, arg19(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}

case class Args20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20]) extends LazyLogging {

  val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20)

  def run[R](func: Function20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def forFunction[R](func: Function20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = apply(func, argState)

  def apply[R](func: Function20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R], argState: ArgState = ArgState())(implicit orig: FunctionOrigin): FuncArg[R] = {

    val mapper = Mapper(args){ (m: FullConfig) => 

       Try {

        logger.debug(s"Called function with args: (${arg1(m).get}, ${arg2(m).get}, ${arg3(m).get}, ${arg4(m).get}, ${arg5(m).get}, ${arg6(m).get}, ${arg7(m).get}, ${arg8(m).get}, ${arg9(m).get}, ${arg10(m).get}, ${arg11(m).get}, ${arg12(m).get}, ${arg13(m).get}, ${arg14(m).get}, ${arg15(m).get}, ${arg16(m).get}, ${arg17(m).get}, ${arg18(m).get}, ${arg19(m).get}, ${arg20(m).get})")
        val res = func(arg1(m).get, arg2(m).get, arg3(m).get, arg4(m).get, arg5(m).get, arg6(m).get, arg7(m).get, arg8(m).get, arg9(m).get, arg10(m).get, arg11(m).get, arg12(m).get, arg13(m).get, arg14(m).get, arg15(m).get, arg16(m).get, arg17(m).get, arg18(m).get, arg19(m).get, arg20(m).get)
        logger.debug(s"Result: ${res}")
        res
      }
    }

    new FuncArg[R](mapper, argState)
  }
}


object Args {

  def apply(): Args0 = Args0()

  def apply[T1](arg1: Arg[T1]): Args1[T1] = Args1(arg1)

  def apply[T1, T2](arg1: Arg[T1], arg2: Arg[T2]): Args2[T1, T2] = Args2(arg1, arg2)

  def apply[T1, T2, T3](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3]): Args3[T1, T2, T3] = Args3(arg1, arg2, arg3)

  def apply[T1, T2, T3, T4](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4]): Args4[T1, T2, T3, T4] = Args4(arg1, arg2, arg3, arg4)

  def apply[T1, T2, T3, T4, T5](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5]): Args5[T1, T2, T3, T4, T5] = Args5(arg1, arg2, arg3, arg4, arg5)

  def apply[T1, T2, T3, T4, T5, T6](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6]): Args6[T1, T2, T3, T4, T5, T6] = Args6(arg1, arg2, arg3, arg4, arg5, arg6)

  def apply[T1, T2, T3, T4, T5, T6, T7](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7]): Args7[T1, T2, T3, T4, T5, T6, T7] = Args7(arg1, arg2, arg3, arg4, arg5, arg6, arg7)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8]): Args8[T1, T2, T3, T4, T5, T6, T7, T8] = Args8(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9]): Args9[T1, T2, T3, T4, T5, T6, T7, T8, T9] = Args9(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10]): Args10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10] = Args10(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11]): Args11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11] = Args11(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12]): Args12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12] = Args12(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13]): Args13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13] = Args13(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14]): Args14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14] = Args14(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15]): Args15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15] = Args15(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16]): Args16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16] = Args16(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17]): Args17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17] = Args17(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18]): Args18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18] = Args18(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19]): Args19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19] = Args19(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19)

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20]): Args20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20] = Args20(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20)

}
