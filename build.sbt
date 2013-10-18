
import AssemblyKeys._

assemblySettings

name := "scala_args"

version := scala.reflect.io.File("version.txt").lines.next.trim

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.2"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"

scalacOptions += "-deprecation"

mainClass in (Compile, run) := Some("example.Run")

mainClass in (Compile, packageBin) := Some("example.Run")

EclipseKeys.classpathTransformerFactories := Seq(IvyCacheTransformer)

publishTo := Some(Resolver.file("file", new File(System.getenv("HOME") + "/publish/scala_args")))
