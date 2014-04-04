package example

import scala.collection.JavaConversions.mapAsScalaMap

import rml.args.arg.AllArgs
import rml.args.arg.Flag
import rml.args.conversions.basic.AnInt
import rml.args.conversions.db.DbFromFile
import rml.args.conversions.db.DbsFromFile
import rml.args.conversions.files.Files
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.JString
import rml.args.domain.Func
import rml.args.manager.FunctionOrigin
import rml.args.manager.FunctionRegister

object RunDiv {

  implicit val origin = FunctionOrigin(getClass)

  val ma = Func(AString("a") -- "aa", AString("b") -- "bb"){ _ + " " + _}
  
  FunctionRegister("remote"::"add"::Nil)   = Func(Flag("v"), AString("url")){ (verbose, url) => if(verbose) println(url) }
  FunctionRegister("generate")             = Func(AnInt("max")){ Func.create }
  FunctionRegister("env")                  = Func(AString("-") -> "" -- "Filter"){ f => 
    import collection.JavaConversions._
    for((k, v) <- System.getenv().toList.sortBy(_._1) if k.startsWith(f)) println(k + "\t" + v)
  }
  FunctionRegister("args")            = Func(AllArgs()){ for((k, v) <- _) println(k + ":\t" + v.mkString("\n\t")) }
  FunctionRegister("dbff")            = Func(DbFromFile("db", Files("dbconf")) -- "Db", JString("-")){(d, s) => println(d); println(s)} -- "Database from file"
  FunctionRegister("dbsff")           = Func(DbsFromFile("db", Files("dbconf")) -- "Db"){println} -- "Databases from file"
  
  FunctionRegister("ma")              = Func(ma){println} -- "MultiArg Test"
}