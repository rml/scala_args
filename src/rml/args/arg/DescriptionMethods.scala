package rml.args.arg

trait DescriptionMethods[+T] { this: T =>

  /**
   * Description
   */
  var description: String = "No description"

  /**
   * Adds a description to an existing object and returns it
   */
  def desc(text: String): T = {
    description = text
    this
  }
  
  /**
   * Adds a description to an existing object and returns it
   */
  def --(text: String) = desc(text)
  
  /**
   * Shows the argument description
   */
  def showdesc: String = description
  
}