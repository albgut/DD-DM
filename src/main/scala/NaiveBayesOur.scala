import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.util.MLUtils

object NaiveBayesOur extends App {
  val conf = new SparkConf().setAppName("MNIST").setMaster("local")
  val pathLoadData :String = "data/train_data"
  val sc: SparkContext = new SparkContext(conf)

  val train = MLUtils.loadLibSVMFile(sc, pathLoadData)

  val Array(training, test) = train.randomSplit(Array(0.6, 0.4))

  val t0 = System.nanoTime

  val modelBayes = NaiveBayes.train(training, lambda = 1.0, modelType = "multinomial")

  val t1 =System.nanoTime()


  val predictionAndLabelBayes = test.map(p => (modelBayes.predict(p.features), p.label))
  val accuracyBayes = 1.0 * predictionAndLabelBayes.filter(x => x._1 == x._2).count() / test.count()

  val text = "#  ACCURACY BAYES = " + accuracyBayes + "     #"
  val size = text.length
  var line = ""
  text.foreach(_ => line += "#")
  println(line)
  println("#  ACCURACY BAYES = " + accuracyBayes + "     #")
  println(line)

  val duration = (System.nanoTime - t0) / 1e9d
  println("duration : "+duration+" s")

}