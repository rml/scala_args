package rml.args.arg

import rml.args.arg.restriction.{NoRestriction, Restriction}

case class ArgState(
    aliases: List[String] = Nil,
    description: String = "",
    restriction: Restriction = NoRestriction
)
