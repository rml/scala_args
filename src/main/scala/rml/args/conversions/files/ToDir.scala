package rml.args.conversions.files

import rml.args.arg.InputArg
import rml.args.arg.decorator.Env
import rml.args.arg.input._
import rml.args.arg.restriction.FileRestricted
import rml.args.exceptions.IllegalArgException

import java.io.File

trait ToDir extends FileRestricted {

  val baseType: String = "File"

  def mapToType(value: String): File = {

    val dir = new File(value)
    if (!dir.exists || dir.isDirectory)
      dir
    else
      throw new IllegalArgException(
        "'" + dir.getAbsolutePath + "' is not a directory"
      )
  }
}

object ADir {
  def apply(key: String): InputArg[File] =
    InputArg(key, new SingleArg[File] with ToDir)
}

object JDir {
  def apply(key: String): InputArg[File] =
    InputArg(key, new JoinArg[File] with ToDir { override val sep = "" })
}

object Dirs {
  def apply(key: String): InputArg[List[File]] =
    InputArg(key, new ListArg[File] with ToDir)
}

object Dirs0 {
  def apply(key: String): InputArg[List[File]] =
    InputArg(key, new ListArg0[File] with ToDir)
}

object PDir {
  def apply(pos: Int): InputArg[File] = InputArg(
    "-",
    new ToDir with PositionalArg[File] { val position: Int = pos }
  )
}

object CwdOrDir {

  def apply(key: String): InputArg[File] = ADir(key) //-> FixArg(new File("."))
}

object HomeOrDir {

  def apply(key: String): InputArg[File] = ADir(key) -> Env(ADir("HOME"))
}
