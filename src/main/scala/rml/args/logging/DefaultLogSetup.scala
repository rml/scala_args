package rml.args.logging

import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.Func
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
  implicit val origin = FunctionOrigin("DefaultLogSetup")

  val logpatterMap = Map(
      "default" -> "%-5level %message%n",
      "withLogger" -> "%-5level %logger %message%n"
  )
  
  val loglevel = Opt(AnEnum("level", LogLevel)) -- "Loglevel"
  val initialloglevel = AnEnum("initialloglevel", LogLevel) -> LogLevel.INFO -- "Loglevel"
  val loglayout = Opt(AMap("layout", logpatterMap)) -- "Loglayout"
  val logpattern = Opt(JString("pattern")) -- "Logpattern"
  val initiallogpattern = JString("initiallogpattern") -> logpatterMap("default") -- "Logpattern"

  val loggers = Opt(DynMaps("logger", () => LoggerManager.getLoggerMap())) -- "Loggers"

  
  def apply(prefix: String, systemPrefix: String = "@") = {

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