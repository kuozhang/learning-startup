package kuo.spark

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xiaoshe on 15/09/2017.
  */
object SparkUtils {

  def newSparkContext(appName: String, master: String): SparkContext = {
    val conf = new SparkConf()
    conf.setAppName(appName)
    conf.setMaster(master)
    new SparkContext(conf)
  }

  def newSQLContext(appName: String, master: String): SQLContext = {
    new SQLContext(new SparkContext(appName, master))
  }

  def newSQLContext(sc: SparkContext): SQLContext = {
    new SQLContext(sc)
  }
}
