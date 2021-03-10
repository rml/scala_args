package rml.args.help

import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.{Func, FuncArg, InputArg}
import rml.args.arg.function.FunctionOrigin
import rml.args.arg.special.Flag
import rml.args.config.FullConfig
import rml.args.conversions.strings.Strings0
import rml.args.register.FunctionRegister

import java.io.PrintStream

case class HelpFunctions(out: PrintStream = System.out) extends LazyLogging {

  implicit val origin: FunctionOrigin = FunctionOrigin(getClass)

  /** Print list of functions, that match the filter
    */
  def printFunctionList(filter: String): Unit = {

    for ((name, func) <- FunctionRegister.list(filter) if name.nonEmpty) {
      out.format("%-15s %s\n", name, func.description)
    }
  }

  def printArg(arg: InputArg[_], indent: String, suffix: String): Unit = {

    out.printf(
      "%-1s -%s %s ** %s **\n",
      "",
      arg.key,
      arg.argState.aliases.map("-" + _).mkString("(", ", ", ")"),
      arg.typeInfo
    )
    out.printf("%-3s %-20s \n\n", "", arg.description)
  }

  /** Print detailed information about one function (temporary solution)
    */
  def printFunctionDescription(
      name: String,
      func: FuncArg[_],
      withOrigin: Boolean = false
  ): Unit = {

    val indent = " " * 2

    val nameAndOrigin =
      name + (if (withOrigin) " (defined in %s)".format(func.origin.origin)
              else "")

    out.println(nameAndOrigin)
//    out.println("_" * nameAndOrigin.length)
    out.println()
    out.println(indent + func.description)
    out.println("\nArguments:\n")

    for (arg <- func.inputArgs) {
      printArg(arg, indent, "")
    }
  }

  /** Print information about registered functions to the console
    */
  def help: FuncArg[Unit] = Func(
    Strings0("filter") ~ "-" -- "Show only functions containing ... ",
    Flag("list") ~ "l" -- "Show list of functions",
    Flag("orig") ~ "origin" -- "Show function origin"
  ) { (filter, list, withOrigin) =>
    val functionRegistered = FunctionRegister.isRegistered(filter)

    if (list || !functionRegistered) {
      printFunctionList(filter.mkString(" "))
    } else {
      printFunctionDescription(
        filter.mkString(" "),
        FunctionRegister.get(filter),
        withOrigin
      )
    }
  }

  def printFullConf(fullConfig: FullConfig): Unit = {

    val allArgs = for {
      config <- fullConfig.allConfigs
      (key, value) <- config.args
    } yield {
      (key, value, config.origin)
    }

    for { (key, values) <- allArgs.groupBy(_._1) } {
      println(key)
      for ((k, v, o) <- values) {
        println(v.mkString("  ", ", ", " (" + o + ")"))
      }
    }

  }
}
