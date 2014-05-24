package rml.args.conversions.files

import scala.reflect.io.File
import scala.reflect.io.Path.string2path

import rml.args.arg.restriction.FileRestricted
import rml.args.arg.InputArg
import rml.args.arg.input.JoinArg
import rml.args.arg.input.ListArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg

trait ToFile extends FileRestricted {
  
  val baseType: String = "File"
    
  def mapToType(value: String): File = File(value)
}


object AFile { def apply(key: String) = InputArg(key, new SingleArg[File] with ToFile) }

object JFile { def apply(key: String) = InputArg(key, new JoinArg[File] with ToFile { override val sep = ""} ) }

object Files { def apply(key: String) = InputArg(key, new ListArg[File] with ToFile) }

object Files0{ def apply(key: String) = InputArg(key, new ListArg0[File] with ToFile) }

object PFile { def apply(pos: Int)    = InputArg("-", new ToFile with PositionalArg[File]{ val position = pos }) }
