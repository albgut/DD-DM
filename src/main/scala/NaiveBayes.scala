import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.util.MLUtils

class NaiveBayes {
  // TODO changer les chemins
  val conf = new SparkConf() // configuration par defaut
  val pathLoadData :String = "data/mllib/sample_libsvm_data.txt"
  val pathSaveModel = "target/tmp/myNaiveBayesModel"
  val sc: SparkContext = new SparkContext(conf)

  // Load and parse the data file.
  val data = MLUtils.loadLibSVMFile(sc, pathLoadData)

  // Split data into training (60%) and test (40%).
  val Array(training, test) = data.randomSplit(Array(0.6, 0.4))

  val model = NaiveBayes.train(training, lambda = 1.0, modelType = "multinomial")

  val predictionAndLabel = test.map(p => (model.predict(p.features), p.label))
  val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()

  // Save and load model
  model.save(sc, "target/tmp/myNaiveBayesModel")
  val sameModel = NaiveBayesModel.load(sc, pathSaveModel)

}
