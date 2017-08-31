name := "kafka-offset-manager"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "0.10.2.0"
//  "ch.qos.logback" % "logback-classic" % "1.2.3",
//  "org.slf4j" % "log4j-over-slf4j" % "1.7.25"
)


