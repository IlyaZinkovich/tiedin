name := "com/tiedin"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "io.spray" % "spray-can" % "1.3.1",
    "io.spray" % "spray-http" % "1.3.1",
    "io.spray" % "spray-routing" % "1.3.1",
    "net.liftweb" % "lift-json" % "2.0",
    "com.typesafe.slick" % "slick_2.11" % "3.1.1",
    "mysql" % "mysql-connector-java" % "5.1.25",
    "com.typesafe.akka" % "akka-actor_2.11" % "2.4.4",
    "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.4",
    "ch.qos.logback" % "logback-classic" % "1.1.7"
)