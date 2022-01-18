import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.{NaiveBayes}
import org.apache.spark.mllib.util.MLUtils

object NaiveBayesOur extends App {
  val conf = new SparkConf().setAppName("MNIST").setMaster("local")
  val pathBigData :String = "data/big_data"
  val pathLoadTrainData :String = "data/train_data"
  val pathLoadTestData : String = "data/test_data"
  val pathSaveModel = "target/tmp/myNaiveBayesModel"
  val sc: SparkContext = new SparkContext(conf)

  val data = MLUtils.loadLibSVMFile(sc, pathBigData)
  //val test = MLUtils.loadLibSVMFile(sc, pathLoadTestData)

  val Array(training, test) = data.randomSplit(Array(0.6, 0.4))

  val model = NaiveBayes.train(data, lambda = 1.0, modelType = "multinomial")

  val predictionAndLabel = test.map(p => (model.predict(p.features), p.label))
  val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()
  println("ACCURACY = " + accuracy)

  //model.save(sc, pathSaveModel)
}