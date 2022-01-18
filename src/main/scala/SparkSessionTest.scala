import org.apache.spark.sql.SparkSession

object SparkSessionTest extends App{
  val spark = SparkSession.builder()
    .master("local")
    .appName("SparkByExample")
    .getOrCreate();

  println("First SparkContext:")
  println("APP Name :"+spark.sparkContext.appName);
  println("Deploy Mode :"+spark.sparkContext.deployMode);
  println("Master :"+spark.sparkContext.master);
}
