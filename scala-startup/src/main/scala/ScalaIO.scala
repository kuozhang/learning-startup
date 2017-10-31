import java.io.{BufferedReader, File, InputStreamReader}

import org.apache.commons.lang3.StringUtils

/**
 *TODO other ways
 */
object ScalaIO {

  def main(args: Array[String]): Unit = {
    readFileByStream()
  }

  def findFileByClassLoader(): Unit = {
    val stream = this.getClass.getClassLoader.getResourceAsStream("test_resource")
    val url = this.getClass.getClassLoader.getResource("test_resource")
    println(stream)
    println(url)
  }

  def readFileByStream(): Unit = {
    val reader = new BufferedReader(new InputStreamReader(this.getClass.getClassLoader.getResourceAsStream("test_resource")))
    val map = Stream.continually(reader.readLine()).takeWhile(_ != null).filter { line =>
      !StringUtils.isBlank(line) && !line.startsWith("#")
    }.map { line =>
      val parts = line.split("\\s+", -1)
      val key = parts(0)
      val value = parts(1)
      (key, value)
    }.toMap

    map.foreach(x => println(x))
  }

}
