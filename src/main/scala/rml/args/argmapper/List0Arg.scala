package rml.args.argmapper

import rml.args.domain.FullConfig

/**
 * Maps each argument of a list individually and returns a list
 * If the argument is missing, it is treated as a list of length 0
 */
trait List0Arg[T] extends ListArg[T] {

  override def noInformationMissing(config: FullConfig) = true
  
  override def apply(config: FullConfig): List[T] =
    if(config.isArg(key)) super.apply(config) else List[T]()
}

