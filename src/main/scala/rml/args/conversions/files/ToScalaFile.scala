package rml.args.conversions.files

import scala.reflect.io.File
import scala.reflect.io.Path.string2path

import rml.args.arg.InputArg
import rml.args.arg.input.JoinArg
import rml.args.arg.input.ListArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.arg.restriction.FileRestricted

trait ToScalaFile extends FileRestricted {
  
  val baseType: String = "ScalaFile"
    
  def mapToType(value: String): File = File(value)
}


object AScalaFile { def apply(key: String) = InputArg(key, new SingleArg[File] with ToScalaFile) }

object JScalaFile { def apply(key: String) = InputArg(key, new JoinArg[File] with ToScalaFile { override val sep = ""} ) }

object ScalaFiles { def apply(key: String) = InputArg(key, new ListArg[File] with ToScalaFile) }

object ScalaFiles0{ def apply(key: String) = InputArg(key, new ListArg0[File] with ToScalaFile) }

object PScalaFile { def apply(pos: Int)    = InputArg("-", new ToScalaFile with PositionalArg[File]{ val position = pos }) }
