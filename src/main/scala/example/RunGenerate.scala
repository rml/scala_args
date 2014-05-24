package example

import rml.args.register.@@
import rml.args.arg.Func
import rml.args.generate.ArgsGenerator
import rml.args.conversions.basic.AnInt

object RunGenerate {

  @@("gen args", "Generate Arg.scala") --> Func(AnInt("maxargs")){
    
    ArgsGenerator.generate(_)
  }
  
}