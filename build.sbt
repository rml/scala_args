
name := "scala_args"

organization := "rml"

scalaVersion := "2.12.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

releaseSettings

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
      )
    case _ =>
      libraryDependencies.value ++ Seq(
      )
  }
}

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "jline" % "jline" % "2.14.2"

scalacOptions += "-deprecation"

scalacOptions += "-feature"

mainClass in (Compile, run) := Some("example.Run")

mainClass in Runtime := Some("example.Run")

mainClass in (Compile, packageBin) := Some("example.Run")
