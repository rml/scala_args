package rml.args.conversions.dates

import java.text.SimpleDateFormat
import java.util.Date
import rml.args.arg.Arg
import rml.args.arg.input.ListArg
import rml.args.arg.input.PositionalArg
import rml.args.arg.input.SingleArg
import rml.args.config.FullConfig
import rml.args.arg.InputArg
import rml.args.arg.input.ListArg0
import rml.args.arg.input.JoinArg
import rml.args.arg.Func
import rml.args.arg.InputCmdMapper
import rml.args.arg.restriction.DateRestricted
import rml.args.arg.FuncArg
import scala.util.Try

trait ToIsoDate extends DateRestricted {
  
  val baseType: String = "Date"
    
  def mapToType(value: String): Date = new SimpleDateFormat("yyyy-MM-dd").parse(value)
}


object AIsoDate { def apply(key: String) = InputArg(key, new SingleArg[Date] with ToIsoDate) }

object AnIsoDate { def apply(key: String) = InputArg(key, new SingleArg[Date] with ToIsoDate) }

object JIsoDate { def apply(key: String) = InputArg(key, new JoinArg[Date] with ToIsoDate { override val sep = "-"} ) }

object IsoDates { def apply(key: String) = InputArg(key, new ListArg[Date] with ToIsoDate) }

object IsoDates0{ def apply(key: String) = InputArg(key, new ListArg0[Date] with ToIsoDate) }

object PIsoDate { def apply(pos: Int)    = InputArg("-", new ToIsoDate with PositionalArg[Date]{ val position = pos }) }


object Today {
  
  def apply(): Arg[Date] = Func{
    
    val f = new SimpleDateFormat("yyyy-MM-dd")
    f.parse(f.format(new Date()))
  }
}
