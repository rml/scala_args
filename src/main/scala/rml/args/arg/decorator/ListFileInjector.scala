package rml.args.arg.decorator
import rml.args.arg.injector.DataFromCsv
import rml.args.arg.InputArg
import rml.args.arg.Arg
import scala.reflect.io.File
import rml.args.arg.injector.ListInjector


case class ListFileInjector[T](
    val arg: Arg[T], 
    files: Arg[List[File]], 
    override val valueArg: InputArg[List[String]],
    val key: Option[String] = None
) extends ListInjector(arg, valueArg, key) with DataFromCsv