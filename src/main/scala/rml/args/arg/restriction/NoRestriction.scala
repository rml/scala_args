package rml.args.arg.restriction

object NoRestriction extends Restriction

trait NotRestricted {

  val getRestriction: NoRestriction.type = NoRestriction
}
