package kuo.spark

/**
  * Created by Kuo Zhang
  */
object SparkSqlApp {

  def main(args: Array[String]): Unit = {
    val appName = "SparkSqlDemo"
    val master = "local[2]"
    val sc = SparkUtils.newSparkContext(appName, master)
    val sqlContext = SparkUtils.newSQLContext(sc)

    import sqlContext.implicits._
    val df = sc.makeRDD(1 to 5).toDF("single")
    df.printSchema()
  }

}
