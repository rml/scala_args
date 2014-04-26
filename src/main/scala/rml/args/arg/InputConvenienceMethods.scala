package rml.args.arg

import rml.args.argdecorator.WithAlias
import rml.args.argdecorator.WithDefault

trait InputConvenienceMethods[+T] { this: InputArg[T] =>

  def withAlias(aliases: String*): InputArg[T] = WithAlias(this, aliases: _*)
  def ~ (aliases: String*): InputArg[T] = withAlias(aliases: _*)
  def + (aliases: String*): InputArg[T] = withAlias(aliases: _*)
  
  def withDefault[X >: T](defaultArgs: Arg[X]*): InputArg[X] = WithDefault(this, defaultArgs: _*)
  def         -> [X >: T](defaultArgs: Arg[X]*): InputArg[X] = WithDefault(this, defaultArgs: _*)
  
  def withDefault[X >: T](default: X): InputArg[X] = WithDefault(this, default)
  def         -> [X >: T](default: X): InputArg[X] = WithDefault(this, default)
  
}