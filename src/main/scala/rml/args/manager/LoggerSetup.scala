package rml.args.manager

import org.slf4j.LoggerFactory

import com.typesafe.scalalogging.slf4j.{LazyLogging => Logging}

import ch.qos.logback.classic.{Level => LogbackLevel}
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender

//sealed abstract class LogLevel
//case object TRACE extends LogLevel
//case object DEBUG extends LogLevel
//case object INFO extends LogLevel
//case object WARN extends LogLevel
//case object ERROR extends LogLevel
//

object LogLevel extends Enumeration {
  type LogLevel = Value
  val ALL, TRACE, DEBUG, INFO, WARN, ERROR, OFF = Value
}


object LoggerSetup extends Logging {
  
  import LogLevel._
  import ch.qos.logback.classic.{Level => LogbackLevel}
  import logger._

  val rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[ch.qos.logback.classic.Logger]
  
  def apply(level: LogLevel) = setLoglevel(level)
  
  def apply() = getLoglevel()
  
  def setLoglevel(level: LogLevel) = {

    val logLevel = level match {
      case ALL   => LogbackLevel.ALL
      case TRACE => LogbackLevel.TRACE
      case DEBUG => LogbackLevel.DEBUG
      case INFO  => LogbackLevel.INFO
      case WARN  => LogbackLevel.WARN
      case ERROR => LogbackLevel.ERROR
      case OFF   => LogbackLevel.OFF
    }
    
    rootLogger.setLevel(logLevel)
  }
  
  def getLoglevel(): LogLevel = {

    rootLogger.getLevel() match {
      case LogbackLevel.ALL   => ALL
      case LogbackLevel.TRACE => TRACE
      case LogbackLevel.DEBUG => DEBUG
      case LogbackLevel.INFO  => INFO
      case LogbackLevel.WARN  => WARN
      case LogbackLevel.ERROR => ERROR
      case LogbackLevel.OFF   => OFF
    }
  }
  
  /**
   * http://logback.qos.ch/manual/layouts.html
   * http://logback.qos.ch/xref/ch/qos/logback/classic/PatternLayout.html
   */
  def setPattern(pattern: String): Unit = {
    
    val loggerContext = rootLogger.getLoggerContext()
    loggerContext.reset()
    
    val encoder = new PatternLayoutEncoder()
    encoder.setContext(loggerContext)
    encoder.setPattern(pattern)
    encoder.start()

    val appender = new ConsoleAppender[ILoggingEvent]()
    appender.setContext(loggerContext)
    appender.setEncoder(encoder)
    appender.start()
    
    rootLogger.addAppender(appender)
    
    info(s"Set logger layout to pattern: '$pattern'")    
  }
  
}