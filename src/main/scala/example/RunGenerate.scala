package example

import rml.args.arg.Func
import rml.args.conversions.basic.AnInt
import rml.args.generate.ArgsGenerator
import rml.args.register.@@

object RunGenerate {

  @@("gen args", "Generate Arg.scala") --> Func(AnInt("maxargs")) {

    ArgsGenerator.generate
  }

}
