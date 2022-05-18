name := "scala_args"

organization := "rml"

scalaVersion := "2.13.8"

publishTo := Some(
  MavenCache(
    "local-maven",
    file(sys.env.getOrElse("HOME", ".") + "/.m2/repository/")
  )
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.3.0",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
      )
    case _ =>
      libraryDependencies.value ++ Seq(
      )
  }
}

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.2.2" % "test",
  "org.jline" % "jline" % "3.19.0",
  "org.fusesource.jansi" % "jansi" % "2.1.0"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

Compile / run / mainClass := Some("example.Run")

Compile / packageBin / mainClass := Some("example.Run")
