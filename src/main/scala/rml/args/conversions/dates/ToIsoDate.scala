package rml.args.conversions.dates

import java.text.SimpleDateFormat
import java.util.Date
import rml.args.arg.Arg
import rml.args.argmapper.List0Arg
import rml.args.argmapper.ListArg
import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg
import rml.args.arg.DependentArg
import rml.args.domain.FullConfig

class ToIsoDate {
  def mapToType(value: String): Date = new SimpleDateFormat("yyyy-MM-dd").parse(value)
}

case class AnIsoDate(val key: String) extends ToIsoDate with SingleArg[Date]

case class IsoDates(val key: String) extends ToIsoDate with ListArg[Date]

case class IsoDates0(val key: String) extends ToIsoDate with List0Arg[Date]

case class PIsoDate(val pos: Int) extends ToIsoDate with PositionalArg[Date]

case class Today() extends DependentArg[Date]{

  val args = List()
  
  override def noInformationMissing(config: FullConfig) = true

  def apply(config: FullConfig): Date = { val f = new SimpleDateFormat("yyyy-MM-dd") ; f.parse(f.format(new Date()))}
}
