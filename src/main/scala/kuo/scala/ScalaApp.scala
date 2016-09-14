package kuo.scala

import scala.collection.mutable.ArrayBuffer

/**
  * @author Kuo Zhang
  */
object ScalaApp {

  def main(args: Array[String]): Unit = {
    // arraySample()
    // mapSample
    // seqSample()
    // operatorsSample()
    // listSample()
    //    collectionOpsSample()
    //    e()

    (List(1, 2, 3, 4) ++: List(5, 6, 7, 8)).foreach(println)
  }


  def reverseSample(): Unit = {
  }

  /**
    * All the opeartions of Array are in the trait ArrayOps
    */
  def arraySample(): Unit = {

    // ***** new an Array  *****

    // immutable length
    val array1 = new Array[Int](3)
    array1(0) = 0
    array1(1) = 1
    array1(2) = 2

    println("Array a:")
    array1.foreach(println)

    val array2 = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    println("Array b:")
    array2.foreach(println)

    // ***** ArrayBuffer, mutable length  *****

    val arrayBuffer = ArrayBuffer[Int]()

    arrayBuffer += 0
    arrayBuffer += (1, 2)
    arrayBuffer ++= Array(3, 4, 5) // note the operator "++="

    //    println("ArrayBuffer:")
    //    arrayBuffer.foreach(println)

    //    println("Remove the last 2 elements:")
    arrayBuffer.trimEnd(2)
    //    arrayBuffer.foreach(println)

    // ***** ArrayBuffer to Array *****
    arrayBuffer.toArray

    // ***** Array to ArrayBuffer *****
    array1.toBuffer

    // ***** traverse Array or ArrayBuffer *****
    for (elem <- array1) // println(elem)
      for (elem <- arrayBuffer) println(elem)

    for (i <- 0 until array1.length) println(array1(i))

    // reverse
    for (i <- (0 until array2.length).reverse) println(array2(i))

    // specify step
    for (i <- 0 until(array2.length, 2)) println(array2(i))

    // ***** yield *****

    // map has the same function
    println("yield the array:")
    val newArray = for (elem <- array1) yield elem * 2
    newArray.foreach(println)

    println("map the array:")
    val newArray2 = array1.map(_ * 2)
    newArray2.map(println)

    // the foreach doesn't return a value, map and yield will return a new array
    // so difference between map and yield ?

    // ***** useful methods ******

    // max
    println("Max value of array2:" + array2.max)

    // min
    println("Min value of array2:" + array2.min)

    //count
    println("number that larger then 5:" + array2.count(_ > 5))

    // sort
    println("Sort the Array:")
    val sortedArray = array2.sorted
    sortedArray.foreach(println)

    println("Sort the Array with user-defined function:")
    // sort with user-defined function
    val sortedArray2 = array2.sortWith(_ > _)
    sortedArray2.foreach(println)

    // what's the sortBy ?
  }


  def mapSample() {
    // ***** new a Map *****

    val immutableMap1 = Map[String, String](("Alice", "10"), ("Bob", "3"), ("Cindy", "8"))
    val immutableMap2 = Map("Alice" -> "10", "Bob" -> "3", "Cindy" -> "3")

    println(immutableMap1.mkString(","))
    println(immutableMap2.mkString(","))

    // ***** get value from map *****

    // difference between map.get() and map(): map.get() returns Some() and None while map() return values or throws an exception if the key is not in the map

    // get the value if the key exists
    println(immutableMap1("Alice"))
    println(immutableMap1.get("Alice"))

    // get the value if the key doesn't exists
    try {
      println(immutableMap1("foo"))
    } catch {
      case ex: Exception => println(ex.getClass.getName)
    }

    println(immutableMap1.get("foo")) // return an Option object, how to use Option?

    println(immutableMap1.getOrElse("foo", 0)) // default value if the key doesn't exist

    // immutable map to mutable map
    val mutableMap = scala.collection.mutable.Map[String, String]() ++ immutableMap1
    println("Immutable map to mutable")
    mutableMap.foreach(println)

    // mutable map to immutable map
    val immutableMap3 = Map[String, String]() ++ mutableMap
    println("Mutable map to immutable")
    immutableMap3.foreach(println)

    // update the mutable map

    // if the key does't exit, then add the (key, value), or update the (key ,value)
    println("if key doesn't exit, add the (key,value)")
    mutableMap("Tony") = "15"
    mutableMap.foreach(println)
    println("if key exits, update the (key,value)")
    mutableMap("Alice") = "5"
    mutableMap.foreach(println)

    // += add multiple KVs or update KVs
    println("mutable map += add and update Kvs")
    mutableMap += ("G.K." -> "10", "Yummy" -> "14", "Bob" -> "5")
    mutableMap.foreach(println)

    // immutable map + add and update KVs, return a new immutable map
    println("immutable map + add and update KVs, and return an new immutable map")
    val immutableMap4 = immutableMap1 + ("G.K." -> "10", "Yummy" -> "14", "Bob" -> "5")
    immutableMap4.foreach(println)

    // immutable map - remove KVs, return a new immutable map
    println("immutable map + add and update KVs, and return an new immutable map")
    val immutableMap5 = immutableMap1 - ("G.K.", "Yummy", "Bob")
    immutableMap5.foreach(println)

    // yield the map
    println("yield the map")
    val immutableMap6 = for ((k, v) <- immutableMap1) yield (v, k) // note if the v is unique
    immutableMap6.foreach(println)

    // map to array of Tuple2
    println("Map to array of Tuple2")
    val mapToArray = immutableMap1.toArray
    println(mapToArray.getClass)

    // tuple to map, an array of Tuple2 can be converted to a map
    println("an array of Tuple2 can be converted to a map")
    val array1 = Array[String]("Alice", "Bob", "Cindy")
    val array2 = Array[String]("10", "5", "8")
    val map = array1.zip(array2).toMap
    //    val tuple = array1.zip(array2)
    //    val map = tuple.toMap
    map.foreach(println)
  }


  def operatorsSample(): Unit = {
    // "=" usually used against Mutable Collection
    val mutableMap1 = scala.collection.mutable.Map[String, String]()
    // operator +=  add elem or (elem1, elem2) to current mutable map
    mutableMap1 += ("1" -> "foo", "2" -> "bar")
    val mutableMap2 = scala.collection.mutable.Map[String, String]()
    mutableMap2 += ("3" -> "hello", "4" -> "world")
    // operator ++=  add all elements of the second mutable map to the first one
    mutableMap1 ++= mutableMap2
    mutableMap1.foreach(println)

    // ++ return a new mutable map
    val mutableMap3 = mutableMap1 ++ mutableMap2
    mutableMap3.foreach(println)
    println(mutableMap3.getClass())

    val arrayBuffer1 = ArrayBuffer[Int]()
    val arrayBuffer2 = ArrayBuffer[Int]()
    // add one element
    arrayBuffer1 += 1
    // add multiple elements
    arrayBuffer1 += (2, 3)
    // add multiple elements
    arrayBuffer2 += (4, 5)
    // add another ArrayBuffer
    arrayBuffer1 ++= arrayBuffer2
    // return a new ArrayBuffer
    val arrayBuffer3 = arrayBuffer1 ++ arrayBuffer2
    println("arrayBuffer1:")
    arrayBuffer1.foreach(println)
    println("arrayBuffer3:")
    arrayBuffer3.foreach(println)


    // operator "++" and "+" applied to Immutable and Mutable Collection, return a new Collection
    val immutableMap1 = Map[String, Int]("foo" -> 1, "bar" -> 2)
    val immutableMap2 = Map[String, Int]("hello" -> 3, "world" -> 4)
    val immutableMap3 = immutableMap1 ++ immutableMap2
    println("Class of immutableMap1 ++ immutableMap2:" + immutableMap3.getClass)
    immutableMap3.foreach(println)

    // Class of immutableMap ++ mutableMap: immutable map
    println("Class of immutableMap ++ mutableMap: " + (immutableMap1 ++ mutableMap1).getClass)
    // Class of mutableMap ++ immutableMap: mutable map
    println("Class of mutableMap ++ immutableMap: " + (mutableMap1 ++ immutableMap1).getClass)
  }

  def listSample(): Unit = {
    // ************************** Immutable List ***********************************
    // new a List
    val list1 = List(1, 2)

    // immutable list, return a new immutable list, add the element to the end
    val list2 = list1 :+ 3

    // add the element to the beginning
    val list3 = 4 +: list2

    // the same as +:
    val list4 = 0 :: list3

    // another way to new a list, list is created from the tail
    val list5 = 5 :: 6 :: Nil

    // add list5 to the end of list4
    val list6 = list4 ++: list5

    list6.foreach(println)

    def sum(list: List[Int]): Int = if (list == Nil) 0 else list.head + sum(list.tail)

    // also the list has a sum method

    println("The sum of list6:" + sum(list6))
    println("Head: " + list6.head)
    println("Head: " + list6.tail)

    // ************************** Mutable List ***********************************

    // ? difference between mutable list and immutable list
    val mList = scala.collection.mutable.LinkedList[Int]()
    println(mList)
  }

  def seqSample(): Unit = {
    //
  }

  def collectionOpsSample(): Unit = {
    val originalMap = Map[String, Int]("foo" -> 1, "bar" -> 2, "hello" -> 3, "world" -> 4)
    val filteredMap = originalMap.filter(kv => kv._2 > 2)
    val filteredNotMap = originalMap.filterNot(kv => kv._2 > 2)
    // the same as val filtered = originalMap.filter(_._2 > 2)
    println("filtered map:")
    println(filteredMap)

    println("filtered not map:")
    println(filteredNotMap)

    val flatMapMap = originalMap.flatMap(kv => Seq(kv._1, kv._2))
    //    val flatMapMap = originalMap.flatMap(kv => List(kv._1, kv._2))
    //    val flatMapMap = originalMap.flatMap(kv => kv._1 :: kv._2 :: Nil)
    println("flat map:")
    println(flatMapMap)

    // index starts from 0, with from, without to
    val sliceMap = originalMap.slice(0, 3)
    println("slice map:")
    println(sliceMap)

    val reduceLeftMap = originalMap.reduceLeft((kv1, kv2) => (kv1._1 + " " + kv2._1, kv1._2 + kv2._2))
    println("reduceLeft map:")
    println(reduceLeftMap)

    val anotherMap = Map[String, Int]("Bob" -> 10, "Alan" -> 9, "Cindy" -> 15)
    val zipMap = originalMap.zip(anotherMap)
    println("zip map:")
    println(zipMap)

    // reduce calls reduceLeft
    val reduceMap = originalMap.reduce((kv1, kv2) => (kv1._1 + " " + kv2._1, kv1._2 + kv2._2))
    println("reduce map;")
    println(reduceMap)

    // fold calls foldLeft
    val foldLeftMap = originalMap.foldLeft("dummy" -> 0)((kv1, kv2) => (kv1._1 + " " + kv2._1, kv1._2 + kv2._2))
    println("foldLeft map:")
    println(foldLeftMap)

    // TODO what's the aggregate
    //    originalMap.aggregate(" ")

    // aggregate does the same as foldLeft in the source, don't know if any implementation does more
    // the comop does nothing
    // difference with foldLeft, the init value(or the zero value can be another type, and return type is the same as
    // that of init value)
    val list = List[String]("This", "is", "an", "example")
    val wholeString = list.aggregate("")({ (x, y) =>
      println("x:" + x)
      println("y:" + y)
      println
      x + y
    }, {
      // this actually does noting
      (m, n) =>
        println("m:" + m)
        println("n:" + n)
        m + n
    })
    println("whole string:")
    println(wholeString)

    val lengthOfString = list.aggregate(0)({ (x, y) =>
      println("x:" + x)
      println("y:" + y)
      println
      0 + y.length
    }, {
      // this actually does noting
      (m, n) =>
        println("m:" + m)
        println("n:" + n)
        m + n
    })
    println("Aggregate length:")
    println(lengthOfString)

    // d
    val groups = list.groupBy(_.contains("a"))
    // t and f are lists
    val t = groups(true)
    val f = groups(false)
    println(t)
    println(f)

    val what = list.grouped(1)
    println("group by size")
    // every element is a List
    while (what.hasNext) println(what.next())
  }

  def e(): Unit = {
    val str = "value\ta:1,b:2,c:3,d:4"
    // filter a, c, return value "value b:2,d:4"
    val tags = str.split("\t")
    //    val name = tags(0)
    val values = tags(1).split(",").filter(kv => {
      val key = kv.split(":")(0)
      key.contains("a") || key.contains("c")
    }).mkString(",")

    println(tags(0) + "\t" + values)
  }

  def operator() {
    //  ++ vs ++:
    // the return type is determined by left side operand when using ++
    // while, the return type is determined by right side operand when using ++:
    List(1, 2) ++ Array(3, 4) // return type is List
    List(1, 2) ++: Array(3, 4) // return type is Array
  }

  def mutableAndImmutable(): Unit = {
    val list = scala.collection.mutable.LinkedList(1, 2, 3)
    list(1)=2
//    pritln
  }

}
