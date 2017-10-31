import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.regex.Pattern

import org.joda.time.{DateTime, Months}
import org.joda.time.format.DateTimeFormat

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting

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
    //    textMonthLength()
    //    testRegex3()
    //    testDateTimeString()
    //    testDateTimeString2()
    //    testMutableSeq()
    //    testArrayBuffer()
    //    testJodaDateTime()
    //    testDivision()
    //    splitWords()
    //    testGetSunday()
    println(testMatchCase(156, 0))
    println(testMatchCase(63, 8))
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

  def testRegex3(): Unit = {
    val startMonth = "2017-01"
    val endMonth = "2017-1"
    val endMonth2 = "22017-1"
    val endMonth3 = "22017-111"
    println(startMonth.matches("^(\\d){4}-(\\d){2}$"))
    println(endMonth.matches("^(\\d){4}-(\\d){2}$"))
    println(endMonth2.matches("^(\\d){4}-(\\d){2}$"))
    println(endMonth3.matches("^(\\d){4}-(\\d){2}$"))
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

  def testJodaDateTime(): Unit = {
    val startMonth = "2017-01"
    val startMonthDate = DateTimeFormat.forPattern("yyyy-MM").parseDateTime(startMonth)
    val endMonthDate = new DateTime().minusMonths(1).dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue()
    var cursor = startMonthDate
    while (cursor.isBefore(endMonthDate)) {
      println(cursor.toString("yyyy-MM-dd"))
      cursor = cursor.plusMonths(1)
    }
  }

  def testGetSunday(): Unit = {
    // the latest Sunday
    var dt = new DateTime().plus(6)
    if (dt.getDayOfWeek != 7) {
      dt = dt.minusDays(7).dayOfWeek().withMaximumValue()
    }
    println(dt.toString("yyyyMMdd"))
  }

  def testDateTimeString(): Unit = {
    println(new DateTime().minusDays(1).toString("yyyyMMdd"))
    println(new DateTime().minusDays(2).toString("yyyyMMdd"))
  }

  def testDateTimeString2(): Unit = {
    val startMonth = "201701"
    val startMonthDate = DateTimeFormat.forPattern("yyyyMM").parseDateTime(startMonth)
    val endMonthDate = new DateTime().minusMonths(1)

    val months = Months.monthsBetween(startMonthDate, endMonthDate)
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

  def testMutableSeq(): Unit = {
    new ArrayBuffer[String]()
    val seq = scala.collection.mutable.Seq[String]()
    seq :+ "foo"
    seq :+ "bar"
    seq.foreach(println(_))
  }

  def testArrayBuffer(): Unit = {
    val buffer = new ArrayBuffer[String]()
    buffer.append("2016-01")
    buffer.append("2016-11")
    buffer.append("2018-01")
    buffer.append("2018-11")
    buffer.append("2017-01")
    buffer.append("2017-02")
    buffer.append("2017-03")
    buffer.append("2017-11")
    buffer.append("2017-12")
    val array = buffer.toArray
    Sorting.quickSort(array)
    array.foreach(println)
  }

  def testDivision(): Unit = {
    val foo1: Int = 1
    val foo2: Int = 3
    println(foo1 / foo2)

    val bar1: Long = 1L
    val bar2: Long = 3L
    println(bar1.toDouble / bar2.toDouble)
  }

  def splitWords(): Unit = {
    val line = "id      user_id mi_id   number  type    real_name       name_py tel     mi_tel  avatar  weixin  reference       weixin_qrcode   shop_name       address home_province_id        home_city_id    home_district_id        home_region_id  home_address    imgs    intro   status  added_time      modified_time   id_card industry_type   monthly_income  tag_ids opened_time     name_certification      sale_score       qr_code        qr_code"
    line.split("(\\s)+", -1).zipWithIndex.foreach(println)
  }

  def testMatchCase(channelId: Int, channel2: Int) = {
    val channelCode = channelId match {
      case 0 => "others"
      case 2 => "ecommerce"
      case 13 => "telecom_operators"
      case 28 => "offline_ka"
      case 62 => "xiaomi_store"
      case 63 => {
        channel2 match {
          case 34 => "mihome_zy"
          case 35 => "mihome_jm"
          case 8 => "mihome_tg"
          case 9 => "mihome_zhtg"
          case 7 => "others"
        }
      }
      case 64 => "zg"
      case 156 => "mihome_yp"
      case 91 => "others"
      case 67 => "overseas_offline"
    }

    channelCode
  }

}
