package rml.args.arg

/**
 * Trait, that maps lists of arguments to the final argument (which can be a list or a single argument).
 * Depending on the ArgMapper, the argument lists that can be processed may have a maximum length. In case the length is
 * limited, the method getUnused returns the trailing arguments.
 */
trait ArgMapper[T] extends Arg[T] {

  /**
   * Result type for a single list element
   */
  type R

  override def allKeysFound(argMap: Map[String, List[String]]) = argMap.contains(key)

  /**
   * returns the arguments not used by this mapper
   */
  def getUnused(argList: List[String]) = List[String]()
  
  /**
   * returns the arguments used by this mapper
   */
  def getUsed(argList: List[String]) = argList
  
  override def apply(argMap: Map[String, List[String]]): T = {
    
    if(!allKeysFound(argMap)) throw new IllegalArgumentException("The argument " + key + " is missing")
    
    val argList = argMap(key)
    
    if(!getUnused(argList).isEmpty) 
      throw new IllegalArgumentException("Found unused trailing value(s) for argument " + key + ": " + 
          getUnused(argList).mkString("'", "', '", "'") + 
          " (the value(s): " + getUsed(argList).mkString("'", "', '", "'") + " is/are sufficient)")
    
    mapListToType(argList)
  }

  /**
   * Maps the input list to the result type
   */
  def mapListToType(argList: List[String]): T
  
  /**
   * Maps a single input list element
   */
  def mapToType(argValue: String): R
  
}