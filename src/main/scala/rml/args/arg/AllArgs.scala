package rml.args.arg

case class AllArgs() extends Arg[Map[String, List[String]]] {

  val key = "[all]"
  
  override def noInformationMissing(argMap: Map[String, List[String]]) = true

  override def apply(argMap: Map[String, List[String]]) = argMap

}