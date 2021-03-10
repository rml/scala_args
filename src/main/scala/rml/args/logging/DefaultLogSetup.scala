package rml.args.logging

import ch.qos.logback.classic.Logger
import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.{Func, FuncArg, InputArg}
import rml.args.arg.decorator.Opt
import rml.args.arg.function.FunctionOrigin
import rml.args.config.reader.ConfReader
import rml.args.conversions.map.AMap
import rml.args.conversions.map.AnEnum
import rml.args.conversions.map.DynMaps
import rml.args.conversions.strings.JString
import rml.args.register.@@

import java.util.logging.LogManager

object DefaultLogSetup extends LazyLogging {

  import logger._
  implicit val origin: FunctionOrigin = FunctionOrigin("DefaultLogSetup")

  val logpatterMap = Map(
      "default" -> "%-5level %message%n",
      "withLogger" -> "%-5level %logger %message%n"
  )
  
  val loglevel: InputArg[Option[LogLevel.Value]] = Opt(AnEnum("level", LogLevel)) -- "Loglevel"
  val initialloglevel: InputArg[LogLevel.Value] = AnEnum("initialloglevel", LogLevel) -> LogLevel.INFO -- "Loglevel"
  val loglayout: InputArg[Option[String]] = Opt(AMap("layout", logpatterMap)) -- "Loglayout"
  val logpattern: InputArg[Option[String]] = Opt(JString("pattern")) -- "Logpattern"
  val initiallogpattern: InputArg[String] = JString("initiallogpattern") -> logpatterMap("default") -- "Logpattern"

  val loggers: InputArg[Option[List[Logger]]] = Opt(DynMaps("logger", () => LoggerManager.getLoggerMap) -- "Loggers")

  
  def apply(prefix: String, systemPrefix: String = "@"): FuncArg[Unit] = {

    LoggerManager.setLoglevel(LogLevel.ERROR)
    val initialConfig = ConfReader("", prefix, "conf")

    initiallogpattern(initialConfig).foreach(LoggerManager.setPattern)
    initialloglevel(initialConfig).foreach(LoggerManager.setLoglevel)

    @@(systemPrefix + "log")-->
    Func(loglevel, loglayout, logpattern, loggers){ (loglevel, layout, pattern, loggers) =>

      loglevel.foreach{ ll =>
        LoggerManager.setLoglevel(ll, loggers.getOrElse(List(LoggerManager.rootLogger)))
      }
      
      layout.foreach(LoggerManager.setPattern)
      pattern.foreach(LoggerManager.setPattern)
      
      println("Loglevel: " + LoggerManager.getLoglevel)
    }
  }
}