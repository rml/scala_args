package rml.args.conversions.files

import rml.args.arg._
import scala.tools.nsc.io.File

class ToPath {
  def mapToType(value: String): File = File(value)
}

case class APath(val key: String) extends ToPath with SingleArg[File]

case class Paths(val key: String) extends ToPath with ListArg[File]

case class PPath(val pos: Int) extends ToPath with PositionalArg[File]

class ToFile {
  def mapToType(value: String): File = {
    val file = File(value)
    if(file.isFile)
      file
    else
      throw new IllegalArgumentException("'" + file.path + "' is not a regular file")
  }
}

case class AFile(val key: String) extends ToFile with SingleArg[File]

case class Files(val key: String) extends ToFile with ListArg[File]

case class PFile(val pos: Int) extends ToFile with PositionalArg[File]

class ToDir {
  def mapToType(value: String): File = {
    val dir = File(value)
    if(dir.isDirectory)
      dir
    else
      throw new IllegalArgumentException("'" + dir.path + "' is not a directory")
  }
}

case class ADir(val key: String) extends ToDir with SingleArg[File]

case class Dirs(val key: String) extends ToDir with ListArg[File]

case class PDir(val pos: Int) extends ToDir with PositionalArg[File]






case class ANewFile(val key: String) extends SingleArg[File] {

  def mapToType(value: String): File = {
    val file = File(value)
    if(file.exists) throw new IllegalArgumentException("The file " + file.path + " exists already.")
    file
  }
}

case class AnExistingFile(val key: String) extends SingleArg[File] {

  def mapToType(value: String): File = {
    val file = File(value)
    if(!file.exists) throw new IllegalArgumentException("The file " + file.path + " does not exist.")

    if(file.isDirectory) throw new IllegalArgumentException("The file " + file.path + " is not a regular file but a directory.")

    file
  }
}

