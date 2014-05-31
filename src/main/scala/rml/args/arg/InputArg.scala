package rml.args.arg

import scala.util.Try

import rml.args.arg.restriction.FixRestriction
import rml.args.arg.restriction.NoRestriction
import rml.args.arg.restriction.Restriction
import rml.args.arg.special.FixArg
import rml.args.config.FullConfig
import rml.args.exceptions.IllegalArgException

final case class InputArg[+T](key: String, mapper: InputMapper[T], argState: ArgState = ArgState(), defaultArgs: List[Arg[T]] = List.empty) extends Arg[T] {

  val description: String = argState.description

  override val typeInfo: String = mapper.typeInfo
  
  def restriction: Restriction = argState.restriction match {
    
    case NoRestriction => mapper.restriction
    case restriction => restriction
  }
  
  override def inputArgs: Set[InputArg[_]] = Set(this)
  
  def defaultArgValue(config: FullConfig): Try[T] = {
    
    defaultArgs.map(_.apply(config)).find(_.isSuccess).getOrElse(throw new IllegalArgException("No default argument for key " + key))
  }
  

  override def apply(config: FullConfig): Try[T] = {

    val mainArg = mapper(config, key, argState.aliases)
    
    val withDefault = if(defaultArgs.isEmpty) mainArg else mainArg.orElse(defaultArgValue(config))
    
    withDefault.orElse(throw new IllegalArgException("No value for key " + key))
  }

  
  private def mkMapper[S](applyFunc: (FullConfig, String, List[String]) => Try[S]): InputMapper[S] = {
    mkMapper("mapper")(applyFunc)
  }
    
  private def mkMapper[S](mapperName: String)(applyFunc: (FullConfig, String, List[String]) => Try[S]): InputMapper[S] = {
    
    new InputMapper[S] {

      override def restriction: Restriction = mapper.restriction

      override def getUnused(argList: List[String]): List[String] = mapper.getUnused(argList)
  
      override def getUsed(argList: List[String]): List[String] = mapper.getUsed(argList)
    
      override def apply(config: FullConfig, key: String, aliases: List[String]): Try[S] = {
        applyFunc(config, key, aliases)
      }
      
      override def toString() = {
        "InputMapper(" + mapperName + ")"
      }
    }
  }
  

  def map[S](mapperName: String)(func: T => S): InputArg[S] = {
    
    val newMapper = mkMapper(mapperName){ (config, key, aliases) =>
      
      val res = mapper.apply(config, key, aliases)
      val resMapped = res.map(func)
      resMapped
    }
    
    InputArg[S](key, newMapper, argState, List.empty)
  }

  override def map[S](func: T => S): InputArg[S] = map("mapper")(func)
  

  def flatMap[S](mapperName: String)(func: T => Try[S]): InputArg[S] = {
    
    val newMapper = mkMapper(mapperName){ (config, key, aliases) =>
      
      val res = mapper.apply(config, key, aliases)
      val resMapped = res.flatMap(func)
      resMapped
    }
    
    InputArg[S](key, newMapper, argState, List.empty)
  }

  override def flatMap[S](func: T => Try[S]): InputArg[S] = flatMap("mapper")(func)

  
  def mapLowLevel[S](mapperName: String)(func: (InputArg[T], FullConfig) => Try[S]): InputArg[S] = {
    
    val newMapper = mkMapper(mapperName){ (config, key, aliases) =>
      
      val resMapped = func(this, config)
      resMapped
    }
    
    InputArg[S](key, newMapper, argState, List.empty)
  }

  
  def mapLowLevel[S](func: (InputArg[T], FullConfig) => Try[S]): InputArg[S] = mapLowLevel("mapper")(func)
    
  
  /**
   * returns the strings not used by this argument
   */
  def getUnused(argList: List[String]): List[String] = mapper.getUnused(argList)
  
  /**
   * returns the strings used by this argument
   */
  def getUsed(argList: List[String]): List[String] = mapper.getUsed(argList)
  
  
  def withAlias(aliases: String*): InputArg[T] = copy(argState = argState.copy(aliases = aliases.toList))
  def        ~ (aliases: String*): InputArg[T] = withAlias(aliases: _*)
  def        + (aliases: String*): InputArg[T] = withAlias(aliases: _*)
  
  def withDefault[X >: T](defaultArgs: Arg[X]*): InputArg[X] = copy(defaultArgs = defaultArgs.toList)
  def         -> [X >: T](defaultArgs: Arg[X]*): InputArg[X] = withDefault(defaultArgs: _*)

  def withDefault[X >: T](default: X): InputArg[X] = withDefault(FixArg(default))
  def         -> [X >: T](default: X): InputArg[X] = withDefault(default)
  
  def withDescription(desc: String): InputArg[T] = copy(argState = argState.copy(description = desc))
  def             -- (desc: String): InputArg[T] = withDescription(desc)

  def withRestriction(restriction: Restriction ): InputArg[T] = copy(argState = argState.copy(restriction = restriction))
  def            |-> (restriction: Restriction ): InputArg[T] = withRestriction(restriction)
  
  def withRestriction(inputValues: List[String]): InputArg[T] = withRestriction(FixRestriction(inputValues.toSet))
  def            |-> (inputValues: List[String]): InputArg[T] = withRestriction(restriction)
  
  
}