import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.evaluation.MulticlassMetrics
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

  val durationBayes = (t1Bayes - t0Bayes) / 1e9d

  val metricsBayes = new MulticlassMetrics(predictionAndLabelBayes)
  val accuracyBayes = metricsBayes.accuracy

  var fMeasureBayes = ""
  metricsBayes.labels.foreach(x => fMeasureBayes += "\t   F-MEASURE FOR " + x.toInt.toString + " = " + metricsBayes.fMeasure(x) + "\n")

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

  val metricsDT = new MulticlassMetrics(predictionAndLabelDT)
  val accuracyDT = metricsDT.accuracy
  var fMeasureDT = ""
  metricsDT.labels.foreach(x => fMeasureDT += "\t   F-MEASURE FOR " + x.toInt.toString + " = " + metricsDT.fMeasure(x) + "\n")

  val durationDT = (t1DT - t0DT) / 1e9d

  // RANDOM FOREST

  val text = "                 ACCURACY BAYES = " + accuracyBayes + "           "
  var line = ""

  text.foreach(_ => line += "=")
  println(line)
  println("                ACCURACY BAYES = " + accuracyBayes + "     ")
  println("              TIME TRAIN BAYES = " + durationBayes + "     ")
  println()
  println(fMeasureBayes)
  println(line)
  println("        ACCURACY DECISION TREE = " + accuracyDT + "     ")
  println("      TIME TRAIN DECISION TREE = " + durationDT + "     ")
  println()
  println(fMeasureDT)
  println(line)
}