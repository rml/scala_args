package rml.args.conversions.db

import rml.args.arg.injector.ArgFromFile
import rml.args.arg.injector.ArgsFromFile
import rml.args.arg.injector.ArgMapFromFile
import rml.args.arg.injector.ArgListFromFile

object DbFromFile extends ArgFromFile(Db())

object DbsFromFile extends ArgsFromFile(Db())

object DbMapFromFile extends ArgMapFromFile(Db())

object DbListFromFile extends ArgListFromFile(Db())

