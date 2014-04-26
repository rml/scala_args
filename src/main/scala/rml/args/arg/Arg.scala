package rml.args.arg

import rml.args.domain.FullConfig

/**
 * Trait that represents an argument of type T
 */
trait Arg[+T] {

  def inputArgs: Set[InputArg[_]]
  
  /**
   * Checks, whether the argument has all information needed to return a meaningful value
   */
  def noInformationMissing(config: FullConfig): Boolean

  /**
   * Takes TODO and returns the argument value of type T
   */
  def apply(config: FullConfig): T
  
}

