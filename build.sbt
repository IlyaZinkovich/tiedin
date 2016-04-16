name := "com/tiedin"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "io.spray" % "spray-can" % "1.3.1",
    "io.spray" % "spray-http" % "1.3.1",
    "io.spray" % "spray-routing" % "1.3.1",
    "net.liftweb" % "lift-json_2.11" % "2.6.3",
    "com.typesafe.slick" %% "slick" % "3.1.0",
    "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
    "com.typesafe.slick"  %% "slick-hikaricp" % "3.1.1",
    "com.typesafe.slick" %% "slick-testkit" % "3.0.0" % "test",
    "com.novocode" % "junit-interface" % "0.10" % "test",
    "mysql" % "mysql-connector-java" % "5.1.36",
    "com.typesafe.akka" % "akka-actor_2.11" % "2.4.4",
    "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.4",
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "org.scalatest" % "scalatest_2.11" % "3.0.0-M15",
    "com.h2database" % "h2" % "1.3.170"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v", "-s", "-a")

parallelExecution in Test := false

logBuffered := false