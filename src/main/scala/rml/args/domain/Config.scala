package rml.args.domain

case class Config(
    args: Map[String, List[String]],
    origin: String
) {
  
  def contains(key: String) = args.contains(key)
  
  def apply(key: String) = args(key)
}