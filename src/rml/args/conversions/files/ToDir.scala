package rml.args.conversions.files

import scala.reflect.io.File
import scala.reflect.io.Path.string2path

import rml.args.arg.FixArg
import rml.args.argdecorator.Env
import rml.args.argdecorator.WithDefault
import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg
import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg
import rml.args.exceptions.IllegalArgException

class ToDir {
  def mapToType(value: String): File = {
    val dir = File(value)
    if(!dir.exists || dir.isDirectory)
      dir
    else
      throw new IllegalArgException("'" + dir.path + "' is not a directory")
  }
}

case class ADir(val key: String) extends ToDir with SingleArg[File]

case class Dirs(val key: String) extends ToDir with ListArg[File]

case class Dirs0(val key: String) extends ToDir with List0Arg[File]

case class PDir(val pos: Int) extends ToDir with PositionalArg[File]

object CwdOrDir{

  def apply(key: String) = WithDefault(ADir("-"), FixArg(File(".")))
}

object HomeOrDir{

  def apply(key: String) = WithDefault(ADir(key), Env(AFile("HOME")))
}

