package example

import rml.args.conversions.strings._
import rml.args.arg.InputArg

object WrapperChaining {

  val name: InputArg[String] = AString("name")
  val jname: InputArg[String] = JString("name")
  val names: InputArg[List[String]] = Strings("name")
  val names0: InputArg[List[String]] = Strings0("name")
  
  val nameA: InputArg[String] = AString("name") ~ "nombre"
  val jnameA: InputArg[String] = JString("name") ~ "nombre"
  val namesA: InputArg[List[String]] = Strings("name") ~ "nombre"
  val names0A: InputArg[List[String]] = Strings0("name") ~ "nombre"
  
  val nameD: InputArg[String] = AString("name") -> "Yoyo"
  val jnameD: InputArg[String] = JString("name") -> "Yoyo"
  val namesD: InputArg[List[String]] = Strings("name") -> ("Yoyo" :: Nil)
  val names0D: InputArg[List[String]] = Strings0("name") -> ("Yoyo" :: Nil)
  
  val nameH: InputArg[String] = AString("name") -- "Your name"
  val jnameH: InputArg[String] = JString("name") -- "Your name"
  val namesH: InputArg[List[String]] = Strings("name") -- "Your name"
  val names0H: InputArg[List[String]] = Strings0("name") -- "Your name"
  
  val nameAD: InputArg[String] = AString("name") ~ "nombre" -> "Yoyo"
  val jnameAD: InputArg[String] = JString("name") ~ "nombre" -> "Yoyo"
  val namesAD: InputArg[List[String]] = Strings("name") ~ "nombre" -> ("Yoyo" :: Nil)
  val names0AD: InputArg[List[String]] = Strings0("name") ~ "nombre" -> ("Yoyo" :: Nil)
    
  val nameAH: InputArg[String] = AString("name") ~ "nombre" -- "Your name"
  val jnameAH: InputArg[String] = JString("name") ~ "nombre" -- "Your name"
  val namesAH: InputArg[List[String]] = Strings("name") ~ "nombre" -- "Your name"
  val names0AH: InputArg[List[String]] = Strings0("name") ~ "nombre" -- "Your name"
  
  val nameDA: InputArg[String] = (AString("name") -> "Yoyo") ~ "nombre"
  val jnameDA: InputArg[String] = (JString("name") -> "Yoyo") ~ "nombre"
  val namesDA: InputArg[List[String]] = (Strings("name") -> ("Yoyo" :: Nil)) ~ "nombre"
  val names0DA: InputArg[List[String]] = (Strings0("name") -> ("Yoyo" :: Nil)) ~ "nombre"
  
  val nameDH: InputArg[String] = AString("name") -> "Yoyo" -- "Your name"
  val jnameDH: InputArg[String] = JString("name") -> "Yoyo" -- "Your name"
  val namesDH: InputArg[List[String]] = (Strings("name") -> ("Yoyo" :: Nil)) -- "Your name"
  val names0DH: InputArg[List[String]] = (Strings0("name") -> ("Yoyo" :: Nil)) -- "Your name"
  
  val nameHA: InputArg[String] = (AString("name") -- "Your name") ~ "nombre"
  val jnameHA: InputArg[String] = (JString("name") -- "Your name") ~ "nombre"
  val namesHA: InputArg[List[String]] = (Strings("name") -- "Your name") ~ "nombre"
  val names0HA: InputArg[List[String]] = (Strings0("name") -- "Your name") ~ "nombre"
  
  val nameHD: InputArg[String] = AString("name") -- "Your name" -> "Yoyo"
  val jnameHD: InputArg[String] = JString("name") -- "Your name" -> "Yoyo"
  val namesHD: InputArg[List[String]] = Strings("name") -- "Your name" -> ("Yoyo" :: Nil)
  val names0HD: InputArg[List[String]] = Strings0("name") -- "Your name" -> ("Yoyo" :: Nil)
  
  val nameADH: InputArg[String] = AString("name") ~ "nombre" -> "Yoyo" -- "Your name"
  val jnameADH: InputArg[String] = JString("name") ~ "nombre" -> "Yoyo" -- "Your name"
  val namesADH: InputArg[List[String]] = Strings("name") ~ "nombre" -> ("Yoyo" :: Nil) -- "Your name"
  val names0ADH: InputArg[List[String]] = Strings0("name") ~ "nombre" -> ("Yoyo" :: Nil) -- "Your name"
    
  val nameHDA: InputArg[String] = AString("name") -- "Your name" -> "Yoyo" withAlias "nombre"
  val jnameHDA: InputArg[String] = JString("name") -- "Your name" -> "Yoyo" + "nombre"
  val namesHDA: InputArg[List[String]] = Strings("name") -- "Your name" -> ("Yoyo" :: Nil) + "nombre"
  val names0HDA: InputArg[List[String]] = Strings0("name") -- "Your name" -> ("Yoyo" :: Nil) + "nombre"
  
}