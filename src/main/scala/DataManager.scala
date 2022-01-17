import java.nio.file.{Files, Paths}

object DataManager extends App{
  //val test_labels = scala.io.Source.fromFile("data/t10k-labels.idx1-ubyte")
  val truc = Files.readAllBytes(Paths.get("data/t10k-labels.idx1-ubyte"))
  //test_labels.getLines().foreach(println)
  truc.take(10).foreach(x => println(x.toBinaryString))
}
