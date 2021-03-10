package rml.args.arg.injector
import rml.args.config.FullConfig

trait Injector {

  def inject(
      config: FullConfig,
      keyColumn: String,
      keyValue: String
  ): FullConfig
}
