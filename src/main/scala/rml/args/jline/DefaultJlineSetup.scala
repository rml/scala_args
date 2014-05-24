package rml.args.jline

import com.typesafe.scalalogging.slf4j.LazyLogging
import rml.args.arg.function.FunctionOrigin
import rml.args.register.FunctionRegister
import rml.args.arg.Func
import rml.args.conversions.strings.Strings0
import rml.args.conversions.strings.AString

object DefaultJlineSetup extends LazyLogging {

  implicit val origin = FunctionOrigin("DefaultJlineSetup")
  
  def apply(prefix: String, systemPrefix: String = "@") = {

    FunctionRegister("") = Func{ JLineConsole.open(prefix.replaceAll("_$", ""), prefix) } -- "Interactive mode"

    def propertiesKey(key: String): String = prefix.toLowerCase.replaceAll("_", ".") + key
    
    FunctionRegister(systemPrefix + "set") = Func(Strings0("-")){ envVar =>
      
      import scala.collection.JavaConversions._
      
      envVar match {
        case Nil => System.getProperties().foreach{ case(k,v) => println(s"$k: $v")}
        case key :: Nil => logger.error(s"Value for key $key missing")
        case key :: value => System.setProperty(propertiesKey(key), value.mkString(" "))
      }
    }

    FunctionRegister(systemPrefix + "unset") = Func(AString("-")){ key =>
      val v = System.getProperties().remove(propertiesKey(key))
      logger.debug(s"Key $key with value $v removed")
    }

  }
}