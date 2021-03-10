package rml.args.arg

import com.typesafe.scalalogging.LazyLogging
import rml.args.arg.restriction.Restriction
import rml.args.config.FullConfig

import scala.util.Try

trait InputCmdMapper[+T] extends InputMapper[T] with LazyLogging {

  override def restriction: Restriction = getRestriction

  def getRestriction: Restriction

  val structureInfo: String = "%s"

  val baseType: String

  override lazy val typeInfo: String = structureInfo.format(baseType)

  def stringArgs(
      config: FullConfig,
      key: String,
      aliases: List[String]
  ): Try[List[String]] = {

    if (aliases.isEmpty) {
      config.argTry(key)
    } else {
      config.argWithAlias(key, aliases: _*)
    }
  }

  def apply(config: FullConfig, key: String, aliases: List[String]): Try[T] = {

    val stringArgsTry = stringArgs(config, key, aliases)

    stringArgsTry
      .map(getUnused)
      .filter(_.nonEmpty)
      .foreach(unused => handleUnusedValues(key, unused: List[String]))

    stringArgsTry.map(getUsed).map(mapListToType)
  }

  def handleUnusedValues(key: String, unused: List[String]): Unit = {
    logger.warn(
      s"Found unused trailing value(s) for argument $key: " + unused.mkString(
        "'",
        "', '",
        "'"
      )
    )
  }

  /** Maps the input list to the result type
    */
  def mapListToType(argList: List[String]): T

  /** Maps a single input list element
    */
  def mapToType(argValue: String): R

}
