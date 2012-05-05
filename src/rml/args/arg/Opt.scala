package rml.args.arg

/**
 * Wrapper, that makes an argument optional
 */
case class Opt[O](arg: Arg[O]) extends Arg[Option[O]] {

  override val key = arg.key

  override val desc: String = arg.desc

  override def allKeysFound(argMap: Map[String, List[String]]) = true

  override def apply(argMap: Map[String, List[String]]): Option[O] = if(arg.allKeysFound(argMap)) Some(arg.apply(argMap)) else None
}