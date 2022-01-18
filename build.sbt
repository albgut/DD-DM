name := "DM"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.0" % Compile
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0" % Compile
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "3.2.0" % "provided"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.3.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "3.3.1" % Test

