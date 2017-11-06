package spark

import org.apache.spark.sql.SparkSession

/**
  * Created by Kuo Zhang
  */
object SparkSqlApp {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("NewSparkSqlApp").getOrCreate()
    dataSetExample(spark)
  }

  // use spark session
  def newWay(): Unit ={
    val sparkSession = SparkSession.builder().master("local[2]").appName("NewSparkSqlApp").getOrCreate()
    // root path is the project base path
    val df = sparkSession.read.json("examples/src/main/resources/people.json")
    df.printSchema()
  }

  def dataframeExample(spark: SparkSession): Unit ={

  }
  
  def dataSetExample(spark: SparkSession): Unit ={
    import spark.implicits._
    // $example on:create_ds$
    // Encoders are created for case classes
    val caseClassDS = Seq(Person("Andy", 32)).toDS()
    caseClassDS.show()
    // +----+---+
    // |name|age|
    // +----+---+
    // |Andy| 32|
    // +----+---+

    // Encoders for most common types are automatically provided by importing spark.implicits._
    val primitiveDS = Seq(1, 2, 3).toDS()
    primitiveDS.map(_ + 1).collect() // Returns: Array(2, 3, 4)

    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name
    val path = "examples/src/main/resources/people.json"
    val peopleDS = spark.read.json(path).as[Person]
    peopleDS.show()
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+
    // $example off:create_ds$
  }

}
