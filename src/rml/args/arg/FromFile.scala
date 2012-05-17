package rml.args.arg
import rml.args.conversions.files.Files
import rml.args.conversions.strings.Strings
import rml.args.util.CsvReader

case class FromFile[T](arg: Arg[T], files: Files, keys: String*) extends Arg[T] {

  val key = files.key

  override def allKeysFound(argMap: Map[String, List[String]]) = files.allKeysFound(argMap)

  def apply(argMap: Map[String, List[String]]): T = {

    if(!allKeysFound(argMap)) throw new IllegalArgumentException("The argument " + files + " is missing")

    if(keys.forall(argMap contains)){

      val searchKey = keys.map{k => (k, argMap(k).head)}.toMap
      
      files.apply(argMap).foreach{ file =>
        CsvReader.findKey(file, searchKey) match {
          case Some(m) => return arg.apply(m.map{case(k,v) => (k,List(v))} ++ argMap)
          case None =>
        }
      }      
    }

    arg.apply(argMap)
  }
}