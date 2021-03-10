import rml.args.arg.function.FunctionOrigin

package object example {

  implicit val origin: FunctionOrigin = FunctionOrigin("example package")

}
