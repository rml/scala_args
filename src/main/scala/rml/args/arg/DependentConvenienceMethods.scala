package rml.args.arg

import rml.args.argdecorator.WithAlias
import rml.args.argdecorator.WithDefault

trait DependantConvenienceMethods[+T] { this: DependentArg[T] =>

  def withDefault[X >: T](defaultArgs: Arg[X]*): DependentArg[X] = WithDefault(this, defaultArgs: _*)
  def         -> [X >: T](defaultArgs: Arg[X]*): DependentArg[X] = WithDefault(this, defaultArgs: _*)
  
  def withDefault[X >: T](default: X): DependentArg[X] = WithDefault(this, default)
  def         -> [X >: T](default: X): DependentArg[X] = WithDefault(this, default)
  
}