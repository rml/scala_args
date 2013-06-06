package rml.args.conversions.dates

import java.text.SimpleDateFormat
import java.util.Date

import rml.args.arg.Arg
import rml.args.argmapper.ListArg
import rml.args.argmapper.PositionalArg
import rml.args.argmapper.SingleArg

class ToIsoDate {
  def mapToType(value: String): Date = new SimpleDateFormat("yyyy-MM-dd").parse(value)
}

case class AnIsoDate(val key: String) extends ToIsoDate with SingleArg[Date]

case class IsoDates(val key: String) extends ToIsoDate with ListArg[Date]

case class PIsoDate(val pos: Int) extends ToIsoDate with PositionalArg[Date]

case class Today extends Arg[Date]{

  val key = "[Today]"
  
  override def noInformationMissing(argMap: Map[String, List[String]]) = true

  def apply(argMap: Map[String, List[String]]): Date = { val f = new SimpleDateFormat("yyyy-MM-dd") ; f.parse(f.format(new Date()))}
}
