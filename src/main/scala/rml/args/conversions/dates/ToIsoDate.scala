package rml.args.conversions.dates

import rml.args.arg.{Func, FuncArg, InputArg}
import rml.args.arg.input._
import rml.args.arg.restriction.DateRestricted

import java.text.SimpleDateFormat
import java.util.Date

trait ToIsoDate extends DateRestricted {

  val baseType: String = "Date"

  def mapToType(value: String): Date =
    new SimpleDateFormat("yyyy-MM-dd").parse(value)
}

object AIsoDate {
  def apply(key: String): InputArg[Date] =
    InputArg(key, new SingleArg[Date] with ToIsoDate)
}

object AnIsoDate {
  def apply(key: String): InputArg[Date] =
    InputArg(key, new SingleArg[Date] with ToIsoDate)
}

object JIsoDate {
  def apply(key: String): InputArg[Date] =
    InputArg(key, new JoinArg[Date] with ToIsoDate { override val sep = "-" })
}

object IsoDates {
  def apply(key: String): InputArg[List[Date]] =
    InputArg(key, new ListArg[Date] with ToIsoDate)
}

object IsoDates0 {
  def apply(key: String): InputArg[List[Date]] =
    InputArg(key, new ListArg0[Date] with ToIsoDate)
}

object PIsoDate {
  def apply(pos: Int): InputArg[Date] =
    InputArg(
      "-",
      new ToIsoDate with PositionalArg[Date] { val position: Int = pos }
    )
}

object Today {

  def apply(): FuncArg[Date] = Func {

    val f = new SimpleDateFormat("yyyy-MM-dd")
    f.parse(f.format(new Date()))
  }
}
