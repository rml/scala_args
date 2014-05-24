package rml.args.arg.injector
import rml.args.config.FullConfig

trait Injector {

  def inject(config: FullConfig, key: Map[String, String]): FullConfig
}