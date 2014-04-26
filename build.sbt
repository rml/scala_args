
import AssemblyKeys._

assemblySettings

name := "scala_args"

version := scala.reflect.io.File("version.txt").lines.next.trim

organization := "rml"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.2"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"

libraryDependencies += "com.typesafe" %% "scalalogging-slf4j" % "1.0.1"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies += "jline" % "jline" % "2.11"

scalacOptions += "-deprecation"

scalacOptions += "-feature"

mainClass in (Compile, run) := Some("example.Run")

mainClass in Runtime := Some("example.Run")

mainClass in (Compile, packageBin) := Some("example.Run")
