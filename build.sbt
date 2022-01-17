name := "DM"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.0" % Compile
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0" % Compile
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "3.2.0" % "provided"