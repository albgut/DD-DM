import org.apache.spark
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

import java.nio.file.{Files, Paths}

object DataManager extends App{


  /*
  //val test_labels = scala.io.Source.fromFile("data/t10k-labels.idx1-ubyte")
  val labels = Files.readAllBytes(Paths.get("data/t10k-labels.idx1-ubyte"))
  val values = Files.readAllBytes(Paths.get("data/t10k-images.idx3-ubyte"))
  //test_labels.getLines().foreach(println)
  labels.take(10).foreach(x => println(x.toBinaryString))
  println("VALUES ----------------------")
  values.take(10).foreach(x => println(x.toBinaryString))

   */
  //val df = spark.read.format("image").option("dropInvalid", true).load("data/mllib/images/origin/kittens")
  val conf = new SparkConf().setAppName("MNIST").setMaster("local")
  val sc = new SparkContext(conf)
  sc.addFile("data/t10k-labels.idx1-ubyte")

}
