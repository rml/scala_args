package rml.args.arg.restriction


object FileRestriction extends Restriction

trait FileRestricted {
  
  val getRestriction = FileRestriction
}