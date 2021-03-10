package rml.args.exceptions

class IllegalArgException(msg: String, source: Throwable)
    extends IllegalArgumentException(msg, source) {

  def this() = this(null, null)

  def this(msg: String) = this(msg, null)

  def this(source: Throwable) = this(null, source)

  def this(argName: String, wrong: String, allowed: List[String]) =
    this(
      "Wrong value for argument '%s' (%s). Allowed values: %s"
        .format(argName, wrong, allowed.mkString("'", "', '", "'")),
      null
    )
}
