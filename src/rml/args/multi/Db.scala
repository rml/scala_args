package rml.args.multi

import rml.args.arg.MultiArg
import rml.args.conversions.strings.AString

object Db {

  def apply(url: String = "url", user: String = "user", pass: String = "pass") = MultiArg(AString(url), AString(user), AString(pass)){
    (_, _, _)
  }

}