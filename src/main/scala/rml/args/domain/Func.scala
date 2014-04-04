package rml.args.domain

import rml.args.arg.Arg
import rml.args.manager.FunctionOrigin

/**
 * Factory for FunctionDefinitions
 */
object Func {

  def apply[R](func: => R)(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List()

    def apply(m: Map[String, List[String]]): R = {
      func
    }
  }

//  def apply[R](func: Function0[R])(implicit orig: FunctionOrigin) = new Function[R]{
//
//    val origin = orig
//
//    override val args = List()
//
//    def apply(m: Map[String, List[String]]): R = {
//      func()
//    }
//  }

  def apply[T1, R](arg1: Arg[T1])(func: Function1[T1, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m))
    }
  }

  def apply[T1, T2, R](arg1: Arg[T1], arg2: Arg[T2])(func: Function2[T1, T2, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m))
    }
  }

  def apply[T1, T2, T3, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3])(func: Function3[T1, T2, T3, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m))
    }
  }

  def apply[T1, T2, T3, T4, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4])(func: Function4[T1, T2, T3, T4, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5])(func: Function5[T1, T2, T3, T4, T5, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6])(func: Function6[T1, T2, T3, T4, T5, T6, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7])(func: Function7[T1, T2, T3, T4, T5, T6, T7, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8])(func: Function8[T1, T2, T3, T4, T5, T6, T7, T8, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9])(func: Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10])(func: Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11])(func: Function11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12])(func: Function12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13])(func: Function13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14])(func: Function14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15])(func: Function15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16])(func: Function16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17])(func: Function17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m), arg17(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18])(func: Function18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m), arg17(m), arg18(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19])(func: Function19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m), arg17(m), arg18(m), arg19(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20])(func: Function20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m), arg17(m), arg18(m), arg19(m), arg20(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20], arg21: Arg[T21])(func: Function21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m), arg17(m), arg18(m), arg19(m), arg20(m), arg21(m))
    }
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](arg1: Arg[T1], arg2: Arg[T2], arg3: Arg[T3], arg4: Arg[T4], arg5: Arg[T5], arg6: Arg[T6], arg7: Arg[T7], arg8: Arg[T8], arg9: Arg[T9], arg10: Arg[T10], arg11: Arg[T11], arg12: Arg[T12], arg13: Arg[T13], arg14: Arg[T14], arg15: Arg[T15], arg16: Arg[T16], arg17: Arg[T17], arg18: Arg[T18], arg19: Arg[T19], arg20: Arg[T20], arg21: Arg[T21], arg22: Arg[T22])(func: Function22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R])(implicit orig: FunctionOrigin) = new Function[R]{

    val origin = orig

    override val args = List(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22)

    def apply(m: Map[String, List[String]]): R = {
      func(arg1(m), arg2(m), arg3(m), arg4(m), arg5(m), arg6(m), arg7(m), arg8(m), arg9(m), arg10(m), arg11(m), arg12(m), arg13(m), arg14(m), arg15(m), arg16(m), arg17(m), arg18(m), arg19(m), arg20(m), arg21(m), arg22(m))
    }
  }

  def create(maxArgCount: Int) {
    
    for(n <- 0 to maxArgCount){
      val r = 1 to n
      val functype = if(r.isEmpty) "[R]" else r.map("T" + _).mkString("[", ", ", ", R]")
      print("  def apply" + functype)
      print("(" + r.map(i => "arg" + i + ": Arg[T" + i + "]").mkString(", ") + ")")
      println("(func: Function" + n + functype + ")(implicit orig: FunctionOrigin) = new Function[R]{")
      println()
      println("    val origin = orig")
      println()
      println("    override val args = " + r.map("arg" + _).mkString("List(", ", ", ")"))
      println()
      println("    def apply(m: Map[String, List[String]]): R = {")
      println("      func(" + r.map("arg" + _ + "(m)").mkString(", ") + ")")
      println("    }")
      println("  }")
      println()
    }
  }
}