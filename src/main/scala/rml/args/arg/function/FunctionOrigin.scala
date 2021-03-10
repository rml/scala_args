package rml.args.arg.function

case class FunctionOrigin(origin: String)

object FunctionOrigin {

  def apply(clazz: Class[_]): FunctionOrigin = FunctionOrigin(
    clazz.getName
  )

  implicit val origin: FunctionOrigin = FunctionOrigin("No origin specified")
}
