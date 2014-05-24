import rml.args.arg.function.FunctionOrigin

package object example {

  implicit val origin = FunctionOrigin("example package")

}