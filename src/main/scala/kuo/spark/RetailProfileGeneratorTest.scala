package kuo.spark

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
  * Created by binwu on 17-7-26.
  */
object RetailProfileGeneratorTest {

  def main(args: Array[String]): Unit = {
    val foo = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("2017-10-11").getMillis
    println(foo)
  }
}
