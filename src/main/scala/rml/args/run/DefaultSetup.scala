package rml.args.run

import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.function.FunctionOrigin
import rml.args.arg.{Func, FuncArg}
import rml.args.config.reader.ConfReader
import rml.args.help.{DefaultHelpSetup, HelpFunctions}
import rml.args.jline.DefaultJlineSetup
import rml.args.logging.DefaultLogSetup
import rml.args.register.@@

import java.io.File

object DefaultSetup extends LazyLogging {

  implicit val origin: FunctionOrigin = FunctionOrigin("DefaultSetup")

  def apply(prefix: String, systemPrefix: String = "@"): FuncArg[Unit] = {

    DefaultLogSetup(prefix, systemPrefix)

    logger.debug("Default setup with prefix '{}'", prefix)

    DefaultJlineSetup(prefix, systemPrefix)

    DefaultHelpSetup(prefix, systemPrefix)

    @@(systemPrefix + "conf", "Show configuration settings") -->
      Func {

        val fullConfig = ConfReader(Array[String](), prefix, "conf")
        HelpFunctions().printFullConf(fullConfig)
      }

    @@(systemPrefix + "conf files", "Show configuration files") -->
      Func {

        println("Default config files:\nFound  File")
        ConfReader
          .defaultConfFilePaths(prefix)
          .foreach(p =>
            println("[" + (if (new File(p).exists) "X" else " ") + "]    " + p)
          )
      }

    @@(systemPrefix + "version", "Show scala_args version") -->
      Func {

        val scalaArgsPackage = getClass.getPackage

        println(
          scalaArgsPackage.getImplementationTitle + ": " + scalaArgsPackage.getImplementationVersion
        )
      }
  }
}
