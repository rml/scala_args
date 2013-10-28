import rml.args.manager.FunctionOrigin

package object example {

  implicit val origin = FunctionOrigin("example package")

}