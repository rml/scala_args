package rml.args.arg.decorator
import rml.args.arg.injector.DataFromCsv
import rml.args.arg.InputArg
import rml.args.arg.Arg
import java.io.File
import rml.args.arg.injector.SingleInjector


case class FileInjector[T](
    override val arg: Arg[T], 
    files: Arg[List[File]], 
    override val valueArg: InputArg[String],
    val key: Option[String] = None
) extends SingleInjector(arg, valueArg, key) with DataFromCsv
