import scala.io._

object Main extends App {
  Source.fromInputStream(System.in)(Codec.UTF8)
    .getLines()
    .drop(1)
    .map(n =>Sheep.lastCall(n.toInt))
    .zipWithIndex
    .map {
      case (result, idx) => s"Case ${idx+1}: $result"
    }
    .foreach(println)
}
