package rml.args.domain

case class Description(text: String) {
  
  def /(func: Function[_]) = func desc text
  def --(func: Function[_]) = /(func)
}


object / {

  def /(x: String) = Description(x)
}
