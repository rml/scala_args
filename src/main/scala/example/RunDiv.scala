package example

import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.arg.Func
import rml.args.conversions.strings.AString
import rml.args.arg.special.Flag
import rml.args.arg.special.AllArgs
import rml.args.conversions.db.DbFromFile
import rml.args.conversions.db.DbsFromFile
import rml.args.conversions.files.Files
import rml.args.conversions.strings.JString

object RunDiv {

  implicit val origin = FunctionOrigin(getClass)

  val ma = Func(AString("a") -- "aa", AString("b") -- "bb"){ _ + " " + _}
  
  FunctionRegister("remote"::"add"::Nil)   = Func(Flag("v"), AString("url")){ (verbose, url) => if(verbose) println(url) }

  FunctionRegister("env")                  = Func(AString("-") -> "" -- "Filter"){ f => 
    import collection.JavaConversions._
    for((k, v) <- System.getenv().toList.sortBy(_._1) if k.startsWith(f)) println(k + "\t" + v)
  }
  FunctionRegister("args")            = Func(AllArgs()){ x => for((k, v) <- x.args) println(k + ":\t" + v.mkString("\n\t")) }
  FunctionRegister("dbff")            = Func(DbFromFile("db", Files("dbconf")) -- "Db", JString("-") -> "nothing"){(d, s) => println(d); println(s)} -- "Database from file"
  FunctionRegister("dbsff")           = Func(DbsFromFile("db", Files("dbconf")) -- "Db"){println} -- "Databases from file"
  
  FunctionRegister("ma")              = Func(ma){println} -- "MultiArg Test"
}