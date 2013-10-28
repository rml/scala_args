package rml.args.domain

import rml.args.arg.Arg
import rml.args.argmapper._
import rml.args.reader.ConfReader
import rml.args.arg.DescriptionMethods
import rml.args.exceptions.IllegalArgException
import rml.args.manager.FunctionOrigin

trait FunctionDefinition[T] extends DescriptionMethods[FunctionDefinition[T]]{

  val origin: FunctionOrigin
  
  val args = List[Arg[_]]()
  
  def getArg(argName: String) = args.find(_.key == argName)
    
  def run(args: FullConfig): T
  
  def apply(config: FullConfig): T = run(findPositionalArgs(config))

  def findPositionalArgs(config: FullConfig): FullConfig = {
    
    if(config.cmdConfig.args.contains("-")) return config
    
    val trailingByArgKey = for{
      arg <- args
      trailing = arg.getUnused(config.args.getOrElse(arg.key, Nil)) if !trailing.isEmpty
    } yield {
      (arg.key, trailing)
    }

    if(trailingByArgKey.isEmpty) return config
    
    if(trailingByArgKey.groupBy(_._1).size > 1) throw new IllegalArgException("too many trailing values")
    
    val trailing = trailingByArgKey.map{ case(k, v) => v }.sortBy(_.size).head
    
    config.adjustPositionalArgs(trailing)
  }
  
  def apply(args: Array[String], prefix: String = ""): T = {
    
    val fullConfig = ConfReader(args, prefix)
    // TODO adjust
    apply(fullConfig)
  }
}