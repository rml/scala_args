package rml.args.manager
import scala.actors.Actor
import rml.args.domain.FunctionDefinition
import rml.args.domain.FunctionArgs
import rml.args.domain.Func
import rml.args.argdecorator.Opt
import rml.args.conversions.strings.PString
import rml.args.exceptions.IllegalArgException
import rml.args.reader.ArgReader
import rml.args.conversions.strings.PString
import rml.args.arg.Flag
import rml.args.conversions.strings.JString

/**
 * Central register for function definitions
 */
object FunctionRegister {

  /**
   * Function definitions are stored in this mutable map
   */
  private val register = scala.collection.mutable.Map[List[String], FunctionDefinition[_]]()

  /**
   * Register a new function
   */
  def set(key: List[String], fdef: FunctionDefinition[_]): Unit = {
    if(register.contains(key))
      throw new IllegalStateException("Function for key " + key.mkString("[", "][", "]") + " already registered. (" + fdef + ")")
    register.put(key, fdef)
  }

  /**
   * Remove a function from the register
   * Returns the function if it was previously registered, otherwise None
   */
  def remove(key: List[String]): Option[FunctionDefinition[_]] = register.remove(key)

  /**
   * Replace a function with a new one
   * Returns the old function if it was previously registered, otherwise None
   */
  def replace(key: List[String], fdef: FunctionDefinition[_]): Option[FunctionDefinition[_]] = {
    val old = remove(key)
    set(key, fdef)
    old
  }

  /**
   * Returns the function definition, that best matches the key.
   */
  def get(key: List[String]): FunctionDefinition[_] = {
    
    getPartKey(key) match {
      case Some(k) => register(k)
      case None => throw new IllegalArgException
    }
  }

  /**
   * Finds the longest registered key, that is fully included in the searched key.
   * Comparison always starts from the root, never in the middle of the key.
   */
  def getPartKey(key: List[String]): Option[List[String]] = {

    for(i <- key.length.to(1, -1) ){

      val partkey = key.take(i)
      if(register.contains(partkey))
        return Some(partkey)

    }
    None
  }

  /**
   * Tries to find an exact match for the key and returns the respective function definition, if one exists.
   */
  def getOption(key: List[String]): Option[FunctionDefinition[_]] = register.get(key)

  /**
   * Checks, whether the key is registered
   */
  def isRegistered(key: List[String]): Boolean = register.contains(key)
  
  /**
   * Try to find a function that matches the FunctionArguments and run it
   */
  def run(args: FunctionArgs): Any = {

    val fullKey = args.func :: args.subfuncs
    val partKey = getPartKey(fullKey) match {
      case Some(k) => k
      case None => throw new IllegalStateException("No definition found for function '" + args.func + "'")
    }

    // Arguments, that are not part of the function name are treated as positional arguments
    def adjust(args: FunctionArgs) = if(fullKey == partKey) args else {
      val (subfncs, posArgs) = args.subfuncs.splitAt(partKey.length - 1)
      if(args.args.contains("-")) throw new IllegalArgException("Too many subfunction arguments: " + subfncs)
      args.copy(subfuncs = subfncs, args = args.args + ("-" -> posArgs))
    }
    
    register(partKey)(adjust(args))
  }

  /**
   * Convenience function
   */
  def apply(key: List[String]) = get(key)
  
  /**
   * Convenience function
   */
  def apply(key: String) = get(key :: Nil)
  
  /**
   * Convenience function
   */
  def update(key: List[String], fdef: FunctionDefinition[_]) = set(key, fdef)
  
  /**
   * Convenience function
   */
  def update(key: String, fdef: FunctionDefinition[_]) = set(key :: Nil, fdef)
  
  /**
   * Convenience function
   */
  def apply(args: FunctionArgs) = run(args)

  /**
   * Print information about registered functions to the console
   */
  def help = Func(Opt(JString("-")), Flag("v")){ (filter, v) =>

    val verbose = v || (filter.isDefined && filter.get.contains(" "))

    val funcs = for{
      (key, func) <- register if !key.isEmpty
      name = key.mkString(" ") if !filter.isDefined || name.startsWith(filter.get)
    } yield (key, name, func)


    if(funcs.isEmpty || !funcs.tail.isEmpty){
   
    	if(verbose){
    	  val max = funcs.map(_._2.size).max
    	  val format = "%-" + (max + 5) + "s%s\n"
    				
    	  for((key, name, func) <- funcs.toList.sortBy(_._2)) printf(format, name, func.description)
    				
    	} else {
    	  funcs.map(_._1.head).toList.distinct.sorted.foreach(println)
    	}
    	
    } else {
      
    	val (key, name, func) = funcs.head
    	val format = "%-" + (key.length + 5) + "s     %-10s %s\n"
    	printf(format, name, func.description, "")

    	for(arg <- func.args) {
    	  printf(format, "  ", arg.key, arg.showdesc)    	  
    	}
    }
  }

}