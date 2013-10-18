package rml.args.conversions.files

import rml.args.arg._
import rml.args.argmapper._
import rml.args.exceptions.IllegalArgException
import scala.reflect.io.File

class ToFile {
  def mapToType(value: String): File = File(value)
}

case class AFile(val key: String) extends ToFile with SingleArg[File]

case class Files(val key: String) extends ToFile with ListArg[File]

case class Files0(val key: String) extends ToFile with List0Arg[File]

case class PFile(val pos: Int) extends ToFile with PositionalArg[File]
