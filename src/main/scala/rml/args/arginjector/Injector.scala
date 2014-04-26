package rml.args.arginjector

import rml.args.arg.Arg
import rml.args.domain.FullConfig

trait Injector {

  def inject(config: FullConfig, key: Map[String, String]): FullConfig
}