package rml.args.conversions.db

import rml.args.arg.MultiArg
import rml.args.conversions.strings.AString
import rml.args.arg.Arg

case class Dbx()

object Db {

  def apply(url: String = "url", user: String = "user", pass: String = "pass") = 
    MultiArg(AString(url), AString(user), AString(pass)){
    (_, _, _)
  }
}
