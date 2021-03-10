package rml.args.conversions.db

import rml.args.arg.{Func, FuncArg}
import rml.args.conversions.strings.AString

object Db {

  def apply(
      url: String = "url",
      user: String = "user",
      pass: String = "pass"
  ): FuncArg[(String, String, String)] =
    Func(AString(url), AString(user), AString(pass)) { (_, _, _) }
}
