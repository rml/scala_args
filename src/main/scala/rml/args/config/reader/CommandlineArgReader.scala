package rml.args.config.reader

import rml.args.config.CmdLineArgs
import rml.args.exceptions.IllegalArgException

import scala.util.parsing.combinator.RegexParsers

/** Parses function name and arguments
  */
object CommandlineArgReader extends RegexParsers {

  private val errorColoring = false

  private def identifier: Parser[String] = """[^- "'][^ "']*""".r

  private def functionName: Parser[String] = identifier

  private def argumentIdentifier: Parser[String] = """[^ "']+""".r

  private def argumentName: Parser[String] = "-" ~> argumentIdentifier

  private def unquotedvalue: Parser[String] = identifier

  private def doublequotedvalue: Parser[String] = "\"" ~> """[^"]+""".r <~ "\""

  private def singlequotedvalue: Parser[String] = "'" ~> """[^']+""".r <~ "'"

  private def argumentValue: Parser[String] =
    unquotedvalue | doublequotedvalue | singlequotedvalue

  private def argumentValues: Parser[List[String]] = rep(argumentValue)

  private def argument: Parser[Tuple2[String, List[String]]] =
    argumentName ~ argumentValues ^^ { case name ~ values =>
      (name, values)
    }

  private def arguments: Parser[List[Tuple2[String, List[String]]]] = rep(
    argument
  )

  private def command: Parser[CmdLineArgs] =
    functionName ~ rep(functionName) ~ arguments ^^ {
      case func ~ subfuncs ~ args =>
        CmdLineArgs(
          func,
          subfuncs,
          args.toMap,
          if (args.isEmpty) "" else args.last._1
        )
    }

  private def parse[T](stringToParse: String, parser: Parser[T]): T = {

    parseAll(parser, stringToParse) match {
      case Success(result, _) => result
      case failure: NoSuccess =>
        throw new IllegalArgException(
          formatError(stringToParse, failure, errorColoring)
        )
    }
  }

  def apply(args: Array[String]): CmdLineArgs = parse(args)

  def apply(args: String): CmdLineArgs = parse(args)

  def parse(args: Array[String]): CmdLineArgs = {

    val argList =
      if (args.isEmpty || (args(0).nonEmpty && args(0).charAt(0) == '-'))
        "" :: args.toList
      else args.toList

    val reversegrouped = argList.tail
      .foldLeft(List(argList.take(1))) { (result, arg) =>
        if (arg.charAt(0) == '-') List(arg) :: result
        else (arg :: result.head) :: result.tail
      }
      .map(_.reverse)

    val lastGroup = reversegrouped.head
    val lastArg =
      if (lastGroup.head.isEmpty || lastGroup.head.charAt(0) != '-') ""
      else lastGroup.head.substring(1)

    val grouped = reversegrouped.reverse

    val funcs = grouped.head
    val mainfunc = funcs.head
    val subfuncs = funcs.tail
    val arguments =
      for (argName :: argValues <- grouped.tail)
        yield (argName.substring(1), argValues)

    CmdLineArgs(mainfunc, subfuncs, arguments.toMap, lastArg)
  }

  def parse(cmd: String): CmdLineArgs = {

    if (cmd.endsWith("-")) {
      val functionArgs = parse(cmd.substring(0, cmd.length - 1))
      return functionArgs.copy(trailingDash = true)
    }

    if (cmd.isEmpty) {

      CmdLineArgs("", Nil, Map())

    } else if (cmd.charAt(0) == '-') {

      val args = parse(cmd, arguments)
      CmdLineArgs("", Nil, args.toMap, args.last._1)

    } else {

      parse(cmd, command)
    }
  }

  def parseArgument(par: String): Tuple2[String, List[String]] =
    parse(par, argument)

  def parseArgumentValues(argValues: String): List[String] =
    parse(argValues, argumentValues)

  private def printError(parsedString: String, err: NoSuccess): Unit = {
    Console.err.println(formatError(parsedString, err, withColor = true))
  }

  private def formatError(
      parsedString: String,
      err: NoSuccess,
      withColor: Boolean
  ): String = {

    val errMsg = new StringBuilder
    errMsg append "Failed to parse '" + parsedString + "'\n"
    errMsg append "Check the indicated position (this is just a guess)\n\n"
    if (withColor) errMsg append Console.RED
    errMsg append parsedString + "\n"
    errMsg append " " * (err.next.pos.column - 1) + "^\n"
    if (withColor) errMsg append Console.BLACK
    errMsg append "Problem: " + err.msg + "\n"
    errMsg.toString
  }

}
