package rml.args.conversions.files

import java.io.File
import scala.reflect.io.Path.string2path

import rml.args.arg.InputArg
import rml.args.arg.decorator.Env
import rml.args.arg.input.JoinArg
import rml.args.arg.input.ListArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.arg.special.FixArg
import rml.args.exceptions.IllegalArgException
import rml.args.arg.restriction.FileRestricted

trait ToDir extends FileRestricted {
  
  val baseType: String = "File"  
  
  def mapToType(value: String): File = {
    
    val dir = new File(value)
    if(!dir.exists || dir.isDirectory)
      dir
    else
      throw new IllegalArgException("'" + dir.getAbsolutePath() + "' is not a directory")
  }
}


object ADir { def apply(key: String) = InputArg(key, new SingleArg[File] with ToDir) }

object JDir { def apply(key: String) = InputArg(key, new JoinArg[File] with ToDir { override val sep = ""} ) }

object Dirs { def apply(key: String) = InputArg(key, new ListArg[File] with ToDir) }

object Dirs0{ def apply(key: String) = InputArg(key, new ListArg0[File] with ToDir) }

object PDir { def apply(pos: Int)    = InputArg("-", new ToDir with PositionalArg[File]{ val position = pos }) }


object CwdOrDir{
  
  def apply(key: String) = ADir(key) -> FixArg(new File("."))
}


object HomeOrDir {

  def apply(key: String) = ADir(key) -> Env(ADir("HOME"))
}

