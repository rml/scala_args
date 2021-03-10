package rml.args.conversions.files

import rml.args.arg.InputArg
import rml.args.arg.input._
import rml.args.arg.restriction.FileRestricted

import java.io.File

trait ToFile extends FileRestricted {

  val baseType: String = "File"

  def mapToType(value: String): File = new File(value)
}

object AFile {
  def apply(key: String): InputArg[File] =
    InputArg(key, new SingleArg[File] with ToFile)
}

object JFile {
  def apply(key: String): InputArg[File] =
    InputArg(key, new JoinArg[File] with ToFile { override val sep = "" })
}

object Files {
  def apply(key: String): InputArg[List[File]] =
    InputArg(key, new ListArg[File] with ToFile)
}

object Files0 {
  def apply(key: String): InputArg[List[File]] =
    InputArg(key, new ListArg0[File] with ToFile)
}

object PFile {
  def apply(pos: Int): InputArg[File] =
    InputArg(
      "-",
      new ToFile with PositionalArg[File] { val position: Int = pos }
    )
}
