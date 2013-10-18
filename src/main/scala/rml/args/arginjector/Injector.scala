package rml.args.arginjector

import rml.args.arg.Arg

trait Injector {

  def inject(argMap: Map[String, List[String]], key: Map[String, String]): Map[String, List[String]]
}