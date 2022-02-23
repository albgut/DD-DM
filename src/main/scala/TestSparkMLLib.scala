import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils

object TestSparkMLLib extends App {
  val conf = new SparkConf().setAppName("MNIST").setMaster("local")
  val pathLoadData :String = "data/train_data"
  val sc: SparkContext = new SparkContext(conf)

  val train = MLUtils.loadLibSVMFile(sc, pathLoadData)

  val Array(training, test) = train.randomSplit(Array(0.6, 0.4))

  // BAYES

  val t0Bayes = System.nanoTime()

  val modelBayes = NaiveBayes.train(training, lambda = 1.0, modelType = "multinomial")

  val t1Bayes = System.nanoTime()


  val predictionAndLabelBayes = test.map(p => (modelBayes.predict(p.features), p.label))
  val accuracyBayes = 1.0 * predictionAndLabelBayes.filter(x => x._1 == x._2).count() / test.count()

  val durationBayes = (t1Bayes - t0Bayes) / 1e9d

  // DECISION TREE

  val numClasses = 10
  val categoricalFeaturesInfo = Map[Int, Int]()
  val impurity = "gini"
  val maxDepth = 10
  val maxBins = 32

  val t0DT = System.nanoTime()

  val modelDT = DecisionTree.trainClassifier(training, numClasses, categoricalFeaturesInfo,
    impurity, maxDepth, maxBins)

  val t1DT = System.nanoTime()

  val predictionAndLabelDT = test.map(p => (modelDT.predict(p.features), p.label))

  val accuracyDT = 1.0 * predictionAndLabelDT.filter(x => x._1 == x._2).count() / test.count()

  val durationDT = (t1DT - t0DT) / 1e9d

  // RANDOM FOREST

  val text = "            ACCURACY BAYES = " + accuracyBayes + "           "
  var line = ""

  text.foreach(_ => line += "=")
  println(line)
  println("           ACCURACY BAYES = " + accuracyBayes + "     ")
  println("         TIME TRAIN BAYES = " + durationBayes + "     ")
  println(line)
  println("   ACCURACY DECISION TREE = " + accuracyDT + "     ")
  println(" TIME TRAIN DECISION TREE = " + durationDT + "     ")
  println(line)
}