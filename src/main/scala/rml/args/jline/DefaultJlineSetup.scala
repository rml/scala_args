package rml.args.jline

import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.Func
import rml.args.arg.function.FunctionOrigin
import rml.args.conversions.strings.{AString, Strings0}
import rml.args.register.FunctionRegister

object DefaultJlineSetup extends LazyLogging {

  implicit val origin: FunctionOrigin = FunctionOrigin("DefaultJlineSetup")

  def apply(prefix: String, systemPrefix: String = "@"): Unit = {

    FunctionRegister("") = Func {
      JLineConsole.open(prefix.replaceAll("_$", ""), prefix)
    } -- "Interactive mode"

    def propertiesKey(key: String): String =
      prefix.toLowerCase.replaceAll("_", ".") + key

    FunctionRegister(systemPrefix + "set") = Func(Strings0("-")) { envVar =>
      import scala.jdk.CollectionConverters._

      envVar match {
        case Nil =>
          System.getProperties.asScala.foreach { case (k, v) =>
            println(s"$k: $v")
          }
        case key :: Nil => logger.error(s"Value for key $key missing")
        case key :: value =>
          System.setProperty(propertiesKey(key), value.mkString(" "))
      }
    }

    FunctionRegister(systemPrefix + "unset") = Func(AString("-")) { key =>
      val v = System.getProperties.remove(propertiesKey(key))
      logger.debug(s"Key $key with value $v removed")
    }

  }
}
