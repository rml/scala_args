package rml.args.arg.function

case class FunctionOrigin(origin: String)

object FunctionOrigin {

  def apply(clazz: Class[_]): FunctionOrigin = FunctionOrigin(clazz.getName.toString)
  
  implicit val origin = FunctionOrigin("No origin specified")
}