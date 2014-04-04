package rml.args.conversions.db

import rml.args.conversions.strings.AString
import rml.args.arg.Arg
import rml.args.domain.Func

case class Dbx()

object Db {

  def apply(url: String = "url", user: String = "user", pass: String = "pass") = 
    Func(AString(url), AString(user), AString(pass)){
    (_, _, _)
  }
}
