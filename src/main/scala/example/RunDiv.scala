package example

import scala.collection.JavaConversions.mapAsScalaMap
import rml.args.arg.Func
import rml.args.arg.function.FunctionOrigin
import rml.args.arg.special.AllArgs
import rml.args.arg.special.Flag
import rml.args.conversions.db.DbFromFile
import rml.args.conversions.db.DbMapFromFile
import rml.args.conversions.db.DbsFromFile
import rml.args.conversions.files.Files
import rml.args.conversions.strings.AString
import rml.args.conversions.strings.JString
import rml.args.register.@@
import rml.args.conversions.db.DbListFromFile

object RunDiv {

  implicit val origin = FunctionOrigin(getClass)

  val ma = Func(AString("a") -- "aa", AString("b") -- "bb"){ _ + " " + _}
  
  @@("remote add") -->
  Func(Flag("v"), AString("url")){ (verbose, url) => if(verbose) println(url) }

  @@("env") -->
  Func(AString("-") -> "" -- "Filter"){ f => 
    import collection.JavaConversions._
    for((k, v) <- System.getenv().toList.sortBy(_._1) if k.startsWith(f)) println(k + "\t" + v)
  }
  
  @@("args") --> 
  Func(AllArgs()){ x => for((k, v) <- x.args) println(k + ":\t" + v.mkString("\n\t")) }
  
  @@("dbff", "Database from file") --> 
  Func(DbFromFile("db", Files("dbconf")) -- "Db", JString("-") -> "nothing"){(d, s) => println(d); println(s)}

  @@("dbsff", "Databases from file") --> 
  Func(DbsFromFile("db", Files("dbconf")) -- "Db"){println}
  
  @@("dbmff", "Database-Map from file") --> 
  Func(DbMapFromFile("db", Files("dbconf")) -- "Db"){println}
  
  @@("dblff", "Database-List from file") --> 
  Func(DbListFromFile(Files("dbconf"))){println}
  
  @@("ma", "MultiArg Test") -->
  Func(ma){println}
}
