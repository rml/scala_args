package rml.args.conversions.db

import rml.args.arg.injector.{
  ArgFromFile,
  ArgListFromFile,
  ArgMapFromFile,
  ArgsFromFile
}

object DbFromFile extends ArgFromFile(Db())

object DbsFromFile extends ArgsFromFile(Db())

object DbMapFromFile extends ArgMapFromFile(Db())

object DbListFromFile extends ArgListFromFile(Db())
