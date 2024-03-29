package rml.args.config

case class Config(
    args: Map[String, List[String]],
    origin: String
) {

  def this(origin: String, args: (String, List[String])*) =
    this(args.toMap, origin)

  def contains(key: String): Boolean = args.contains(key)

  def apply(key: String): List[String] = args(key)
}

object Config {

  def simple(origin: String, args: (String, String)*): Config =
    Config(args.map { case (k, v) => (k, List(v)) }.toMap, origin)
}
