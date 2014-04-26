package rml.args.manager

import org.slf4j.LoggerFactory

import ch.qos.logback.classic.{Level => LogbackLevel}

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


object LoggerSetup {
  
  import LogLevel._
  import ch.qos.logback.classic.{Level => LogbackLevel}

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
}