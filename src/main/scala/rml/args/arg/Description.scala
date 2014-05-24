package rml.args.arg

case class Description(text: String) {
  
  def /(func: FuncArg[_]) = func -- text
  def --(func: FuncArg[_]) = /(func)
}


object / {

  def /(x: String) = Description(x)
}
