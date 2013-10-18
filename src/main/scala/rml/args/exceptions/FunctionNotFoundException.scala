package rml.args.exceptions

class FunctionNotFoundException(val function: List[String], source: Throwable) extends IllegalStateException(function.mkString(" "), source) {

  def this() = this(null, null)

  def this(function: List[String]) = this(function, null)

  def this(source: Throwable) = this(null, source)
}