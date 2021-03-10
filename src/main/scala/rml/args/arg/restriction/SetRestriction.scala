package rml.args.arg.restriction

trait SetRestriction extends Restriction {

  def allowed: Set[String]
}

case class FixRestriction(allowed: Set[String]) extends SetRestriction
