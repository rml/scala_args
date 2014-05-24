package rml.args.arg

object Func {

  def apply[R](func: => R) = Args0()(func)

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

}
