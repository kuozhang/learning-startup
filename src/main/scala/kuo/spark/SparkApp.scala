package kuo.spark

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Kuo Zhang
  */
object SparkApp {

  def main(args: Array[String]) {

    // create Spark context with Spark configuration
    val conf = new SparkConf()
    conf.setAppName("Sample")
    conf.setMaster("local[4]")
    val sc = new SparkContext(conf)

    //    fromHadoopFile(sc)
    //    fromLocalFile(sc)
    //    worldCount(sc)

        writeSequenceFile(sc)
//    fromSequenceFile(sc)
  }

  def fromHadoopFile(sc: SparkContext) {
    val count = sc.textFile("hdfs://localhost:9000/spark/foo.txt").count()
    println("Line Count:" + count)
  }

  // Key and value must be the type of Writable, like:
  // Int -> IntWritable
  // Long -> LongWritable
  // String -> Text
  // Byte -> ByteWritable
  // Bytes -> BytesWritable
  // see classes in package org.apache.hadoop.io for more
  def writeSequenceFile(sc: SparkContext) {
    val data = sc.parallelize(List(("key1", 1), ("Key2", 2), ("Key3", 3)))
//    data.saveAsSequenceFile("hdfs://localhost:9000/spark/sequenceExample2/")
    data.saveAsSequenceFile("/home/kuo/spark/tmp/sequenceExample2/")
  }

  def fromSequenceFile(sc: SparkContext) {
    val path = "hdfs://localhost:9000/spark/sequenceExample2/"
    val result = sc.sequenceFile[Text, IntWritable](path, classOf[Text], classOf[IntWritable]).map ( kv => (kv._1.toString, kv._2.get()) ).collect()
    //    result.foreach(t => {
    //      val tuple = asInstanceOf[Tuple2[String, Int]]
    //      println(tuple._1, tuple._2)
    //    })
    for (ele <- result) println(ele)
  }

  def fromLocalFile(sc: SparkContext) {
    val count = sc.textFile("/home/kuo/foo.txt").count()
    println("Line Count:" + count)
  }

  def fromParquetFile(sc: SparkContext) {
    val sqlContext = new SQLContext(sc)
    sqlContext.read.parquet("") // TODO add a path in the local hdfs, return value is a DataFream
  }

  def worldCount(sc: SparkContext): Unit = {
    val fileRDD = sc.textFile("hdfs://localhost:9000/home/kuo/foo.txt")

    val resultRDD = fileRDD
      .flatMap(_.split("\\s+"))
      .filter(_.nonEmpty)
      .map(w => (w, 1))
      .reduceByKey((a, b) => a + b)
      .saveAsTextFile("hdfs://locahost:9000/spark/worldcount") // save as directory
  }

}
