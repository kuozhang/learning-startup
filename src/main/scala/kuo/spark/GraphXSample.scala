package kuo.spark

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

/**
 * @author Kuo Zhang
 */
object GraphXSample {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("Spark GraphX Sample")

    // Assume the SparkContext has already been constructed
    val sc: SparkContext = new SparkContext(sparkConf)

    // Create an RDD for the vertices
    val users: RDD[(VertexId, (String, String))] =
      sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
        (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))

    // Create an RDD for edges
    val relationships: RDD[Edge[String]] =
      sc.parallelize(Array(Edge(3L, 7L, "collab"), Edge(5L, 3L, "advisor"),
        Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))

    // Define a default user in case there are relationship with missing user
    val defaultUser = ("John Doe", "Missing")

    // Build the initial Graph
    val graph = Graph(users, relationships, defaultUser)

    // view of vertices
    // graph.vertices

    // view of edges
    //    graph.edges.foreach(println)

    // view of triplets
    //    graph.triplets

    // change the attr of edges, also attrs of vertices passed in, if not needed, use mapEdges
    graph.mapTriplets(triplet => triplet.attr + "foo").triplets.foreach(println)

    // change the attr of vertices, return a new graph
    //    graph.mapVertices((vid, vdata) => (vdata._1 + "foo", vdata._2 + "bar")).vertices.foreach(println)

    // only change the property(attr) of edges, return a new graph
    //    graph.mapEdges(edge => edge.attr + "foo").edges.foreach(println)
    //    println(graph.edges.getClass)
    //    println(graph.vertices.getClass)
  }
}
