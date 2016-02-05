organization := "com.ibm.meetup"
name := "interactive"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % "0.12.1",
  "org.http4s" %% "http4s-dsl"          % "0.12.1",
  "org.http4s" %% "http4s-argonaut"     % "0.12.1"

)

libraryDependencies +=  "org.apache.toree" % "toree-client_2.10" % "0.0.0-dev-SNAPSHOT"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.6.3"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.3"

resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"
libraryDependencies += "spark.jobserver" % "job-server-api_2.10" % "0.6.1"
libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.5.1"
libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.5.1"
