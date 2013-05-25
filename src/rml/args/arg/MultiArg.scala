package rml.args.arg
import rml.args.domain.FunctionArgs
import javax.naming.OperationNotSupportedException

/**
 * Trait that combines multiple arguments into one argument
 */
trait MultiArg[T] extends Arg[T] {

  val args: List[Arg[_]]
  
  override val key: String = "[Multi Arg]"
    
  override def noInformationMissing(argMap: Map[String, List[String]]): Boolean = args.forall(_.noInformationMissing(argMap))
  
  override def toString = args.mkString("MultiArg(", ", ", ")")
}

object MultiArg {

  def apply[T1, T2, R](arg1: Arg[T1], arg2: Arg[T2])(func: Function2[T1, T2, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args))
  }

  def apply[T1, T2, T3, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3])(func: Function3[T1, T2, T3, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args))
  }

  def apply[T1, T2, T3, T4, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4])(func: Function4[T1, T2, T3, T4, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args))
  }

  def apply[T1, T2, T3, T4, T5, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5])(func: Function5[T1, T2, T3, T4, T5, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6])(func: Function6[T1, T2, T3, T4, T5, T6, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7])(func: Function7[T1, T2, T3, T4, T5, T6, T7, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8])(func: Function8[T1, T2, T3, T4, T5, T6, T7, T8, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9])(func: Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10])(func: Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11])(func: Function11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12])(func: Function12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13])(func: Function13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14])(func: Function14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15])(func: Function15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16])(func: Function16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17])(func: Function17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args), arg17(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18])(func: Function18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args), arg17(args), arg18(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19])(func: Function19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args), arg17(args), arg18(args), arg19(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20])(func: Function20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args), arg17(args), arg18(args), arg19(args), arg20(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20], arg21: Arg[T21])(func: Function21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args), arg17(args), arg18(args), arg19(args), arg20(args), arg21(args))
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20], arg21: Arg[T21], arg22: Arg[T22])(func: Function22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R]) = new MultiArg[R] {

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22)

    override def apply(args: Map[String, List[String]]): R = func(arg1(args), arg2(args), arg3(args), arg4(args), arg5(args), arg6(args), arg7(args), arg8(args), arg9(args), arg10(args), arg11(args), arg12(args), arg13(args), arg14(args), arg15(args), arg16(args), arg17(args), arg18(args), arg19(args), arg20(args), arg21(args), arg22(args))
  }
  
  def create(maxArgCount: Int) {
    
    for(n <- 2 to maxArgCount){
      val r = 1 to n
      val functype = r.map("T" + _).mkString("[", ", ", ", R]")
      print("  def apply" + functype)
      print("(" + r.map(i => "arg" + i + ": Arg[T" + i + "]").mkString(", ") + ")")
      println("(func: Function" + n + functype + ") = new MultiArg[R] {")
      println()
      println("    override val args = " + r.map("arg" + _).mkString("List(", ", ", ")"))
      println()
      println("    override def apply(args: Map[String, List[String]]): R = func(" + r.map("arg" + _ + "(args)").mkString(", ") + ")")
      println("  }")
      println()
    }
  }
}
