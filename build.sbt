
import AssemblyKeys._

assemblySettings

name := "scala_args"

version := scala.reflect.io.File("version.txt").lines.next.trim

organization := "rml"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.11.0")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
      )
    case _ =>
      libraryDependencies.value ++ Seq(
      )
  }
}

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.5" % "test"

libraryDependencies += "jline" % "jline" % "2.11"

scalacOptions += "-deprecation"

scalacOptions += "-feature"

mainClass in (Compile, run) := Some("example.Run")

mainClass in Runtime := Some("example.Run")

mainClass in (Compile, packageBin) := Some("example.Run")
