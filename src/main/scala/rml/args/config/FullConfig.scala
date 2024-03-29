package rml.args.config

import com.typesafe.scalalogging.{LazyLogging => Logging}
import rml.args.exceptions.IllegalArgException

import java.text.SimpleDateFormat
import java.util.Date
import scala.util.{Failure, Success, Try}

case class FullConfig(
    cmdConfig: Config,
    configs: List[Config],
    func: String,
    subfuncs: List[String]
) extends Logging {

  import logger._

  def over(key: String, vals: List[String]): FullConfig = over(Map(key -> vals))

  def over(args: Map[String, List[String]]): FullConfig = over(
    Config(args, new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date()))
  )

  def over(config: Config): FullConfig =
    FullConfig(cmdConfig, config :: configs, func, subfuncs)

  val funcName: List[String] = func :: subfuncs

  val allConfigs: List[Config] = cmdConfig :: configs

  // cmdArgs
  val cmdArgs: Map[String, List[String]] = cmdConfig.args

  def isCmdArg(key: String): Boolean = cmdArgs.contains(key)

  def cmdArg(key: String): List[String] = cmdArgs(key)

  // posArgs
  val posArgs: Array[String] = cmdArgs.getOrElse("-", Nil).toArray

  val hasPosArgs: Boolean = !posArgs.isEmpty

  def posArg(pos: Int): String = if (pos < posArgs.length) posArgs(pos) else ""

  // args
  val args: Map[String, List[String]] =
    allConfigs.reverse.map(_.args).reduce(_ ++ _)

  def isArg(key: String): Boolean = args.contains(key)

  def arg(key: String): List[String] = args(key)

  def argTry(key: String): Try[List[String]] = Try(args(key))

  def argWithAlias(key: String, aliases: String*): Try[List[String]] = {

    val keys = key :: aliases.toList

    debug("argWithAlias(%s)", keys.mkString(", "))

    for {
      conf <- allConfigs
      key <- keys
    } {
      val found = conf.args.contains(key)

      if (found) {
        debug(
          "Key {} with value {} found in config {}",
          key,
          conf.args(key),
          conf.origin
        )
        return Success(conf.args(key))
      } else {
        debug("Key {} not found in config {}", key, conf.origin)
      }
    }

    debug("argWithAlias: Nothing found for: %s", keys.mkString(", "))

    Failure(
      new IllegalArgException(
        "argWithAlias: Nothing found for: %s".format(keys.mkString(", "))
      )
    )
  }

  override def toString: String = {

    def formatCfg(cfg: Config): String =
      "%s: %s".format(cfg.origin, cfg.args.mkString(", "))

    "%s %s%s\nargs: %s".format(
      func,
      subfuncs.mkString(" "),
      allConfigs.map(formatCfg).mkString("\n  ", "\n  ", ""),
      args
    )
  }

}
