package rml.args.arg.restriction

object DateRestriction extends Restriction

trait DateRestricted {

  val getRestriction = DateRestriction
}