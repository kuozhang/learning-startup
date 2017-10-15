package kuo.scala

import java.text.SimpleDateFormat
import java.util.regex.Pattern
import java.util.{Calendar, Date}

import org.joda.time.{DateTime, Months}

object Misc {


  def main(args: Array[String]): Unit = {
    //    val date = new SimpleDateFormat("yyyyMMdd").parse("20160910")
    //    val calendar = Calendar.getInstance()
    //    calendar.setTime(date)
    //    println(calendar.get(Calendar.DAY_OF_MONTH))
    //    print0ToBinaryString
    //    printCurrentTime()
    //    testSimpleDateFormat()
    //    convertBetweenBinaryAndLong()
    //    makeString()
    //    testLength()
    //    testRegex2()
    //    getJodaDateTime()
    textMonthLength()
  }

  def print0ToBinaryString: Unit = {
    println("0".toLong.toBinaryString)
    println("00".toLong.toBinaryString)
  }

  def printCurrentTime(): Unit = {
    val date = Calendar.getInstance().getTime
    println(new SimpleDateFormat("yyyyMMdd").format(date))
  }

  def testSimpleDateFormat(): Unit = {
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val date = sdf.parse("2017-09-26")
    println(s"dateToString:${date.toString}")
    println(s"format string:${sdf.format(date)}")

    // yesterday
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    println(s"format string:${sdf.format(calendar.getTime())}")
  }

  def foo(): Unit = {
    val date = new SimpleDateFormat("yyyyMMdd").parse("20160910")
    val calendar = Calendar.getInstance
    calendar.setTime(date)
    val activeString = Option("67637282,33686532,67635216,268698632,524304,16810241,494849505,1940980087,853103118,134348820,420828320,540836885,639871715,1073609735,2013265383,2146959359,1040121855,1610612735,536928255,1610612750,2146957315,536606975,1072611295,191,0,268433408")
    val activeMonths = activeString.getOrElse("").split(",", -1).reverse
    var activeDays = ""
    val offset = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - activeMonths(0).length
    for (raw <- activeMonths) {
      val binaryRaw = raw.toLong.toBinaryString
      activeDays += ("0" * (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - binaryRaw.length) + binaryRaw)
      calendar.add(Calendar.MONTH, -1)
    }
    val activeDays30 = activeDays.substring(offset, if (offset + 30 < activeDays.length) offset + 30 else activeDays.length)
    val activeDays14 = activeDays.substring(offset, if (offset + 14 < activeDays.length) offset + 14 else activeDays.length)
    val activeDaysCount30 = activeDays30.filter(x => x.equals('1')).length
    val activeDaysCount14 = activeDays14.filter(x => x.equals('1')).length
    println(s"activeDay14: ${activeDays14}")
    println(s"activeDayCount14: ${activeDaysCount14}")
    println(s"activeDay30: ${activeDays30}")
    println(s"activeDayCount30: ${activeDaysCount30}")
  }

  def convertBetweenBinaryAndLong(): Unit = {
    val original = "271581184"
    val binStr = original.toLong.toBinaryString
    val parsedLong = java.lang.Long.parseLong(binStr, 2)
    println(s"original: ${original}")
    println(s"binStr: ${binStr}")
    println(s"parsedLong ${parsedLong}")
  }

  def makeString(): Unit = {
    //    val binStr = "1" * 16 + "0" * 9
    val binStr = "1" * 2 + "0" * 29
    val parsedLong = java.lang.Long.parseLong(binStr, 2)
    println(s"parsedLong: ${parsedLong}")
    println(s"binary string: ${parsedLong.toBinaryString}")
  }

  def testLength(): Unit = {
    val foo = Array()
    val bar = Array("")
    println(foo.length)
    println(bar.length)
    for (raw <- bar) {
      println(raw.toLong.toBinaryString)
    }
  }

  def testRegex(): Unit = {
    val date = "2017-09-10"
    val pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}")
    var matcher = pattern.matcher(date)
    matcher.find()
    println(matcher.matches())

    val date1 = "2017-09-101"
    matcher = pattern.matcher(date1)
    println(matcher.matches())
  }

  def testRegex2(): Unit = {
    val date1 = "year=2017/month=10/day=11"
    val date2 = "year=2017/month=10/day=12"
    val date3 = "year=2017/month=10/day=13"
    val r = "^year=(\\d{4})/month=(\\d{2})/day=(\\d{2})$".r
    val dates = Seq(date1, date2, date3).map {
      case r(year, month, day) => s"date=$year$month$day"
    }

    dates.foreach(println)
  }

  def getJodaDateTime() {
    //    val timeLong = 1479451937760L
    val timeLong = 1453099937000L
    val dateTime = new DateTime(timeLong)
    val month = dateTime.getMonthOfYear.toString
    val year = dateTime.getYear.toString
    val day = dateTime.getDayOfMonth
    println(s"$year$month$day")
  }

  def textMonthLength(): Unit = {
    val start20170101 = new DateTime(1483200000000L)
    val end20170201 = new DateTime(1485878400000L)
    val end20180101 = new DateTime(1514736000000L)
    val end20170131 = new DateTime(1485792000000L)

    val start20170110 = new DateTime(1483977600000L)
    val end20170209 = new DateTime(1486569600000L)

    println(Months.monthsBetween(start20170101, end20170201).getMonths)
    println(Months.monthsBetween(start20170101, end20180101).getMonths)
    println(Months.monthsBetween(start20170101, end20170131).getMonths)
    println(Months.monthsBetween(start20170110, end20170209).getMonths)
    println(Months.monthsBetween(end20170201, start20170101).getMonths)
  }

}
