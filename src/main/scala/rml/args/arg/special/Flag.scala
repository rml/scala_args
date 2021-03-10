package rml.args.arg.special

import rml.args.arg.{InputArg, InputMapper}
import rml.args.config.FullConfig
import rml.args.exceptions.IllegalArgException

import scala.util.Try

/** Optional boolean argument. If the key exists, it is true, if the key is absent, it is false.
  */
object Flag {

  val mapper: InputMapper[Boolean] = new InputMapper[Boolean] {

    override lazy val typeInfo: String = "Flag"

    override def getUnused(argList: List[String]): List[String] = argList

    override def getUsed(argList: List[String]): List[String] = List.empty

    private def checkNoValues(values: List[String], key: String): Unit = if (
      values.nonEmpty
    ) {

      throw new IllegalArgException(
        "The flag " + key + " should not have any values (found " + values
          .mkString("'", "', '", "')")
      )
    }

    override def apply(
        config: FullConfig,
        key: String,
        aliases: List[String]
    ): Try[Boolean] = {

      val flag = config.isArg(key)

      if (flag) {
        checkNoValues(config.arg(key), key)
      }

      Try(flag)
    }
  }

  def apply(key: String): InputArg[Boolean] = InputArg(key, mapper)

}
