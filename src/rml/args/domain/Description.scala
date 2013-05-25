package rml.args.domain

case class Description(text: String) {
  
  def /(func: FunctionDefinition[_]) = func desc text
  def --(func: FunctionDefinition[_]) = /(func)
}


object / {

  def /(x: String) = Description(x)
}
