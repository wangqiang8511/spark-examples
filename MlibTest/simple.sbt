name := "Simple Mlib Project"

version := "1.0"

scalaVersion := "2.9.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "0.8.0-incubating",
  "org.apache.spark" %% "spark-mllib" % "0.8.0-incubating"
)

resolvers += "Akka Repository" at "http://repo.akka.io/release/"
