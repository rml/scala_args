package rml.args.arg
import rml.args.argdecorator.WithAlias
import rml.args.argdecorator.WithDefault

trait ConvenienceMethods[+T] { this: Arg[T] =>

  def withDefault[X >: T](defaultArgs: Arg[X]*): Arg[X] = WithDefault(this, defaultArgs: _*)
  def -> [X >: T](defaultArgs: Arg[X]*): Arg[X] = WithDefault(this, defaultArgs: _*)
  
  def withDefault[X >: T](default: X): Arg[X] = new WithDefault(this, default)
  def -> [X >: T](default: X): Arg[X] = new WithDefault(this, default)

  def withAlias(aliases: String*): Arg[T] = WithAlias(this, aliases: _*)
  def ~ (aliases: String*): Arg[T] = withAlias(aliases: _*)
  def + (aliases: String*): Arg[T] = withAlias(aliases: _*)
  
}