import java.io._

import scala.util._
import scala.io._

object Main extends App {
  val input = Try(args(0))
    .map(file => { println(s"Reading $file"); new FileInputStream(file) })
    .getOrElse(System.in)
  val output = new OutputStreamWriter(Try(args(1))
    .map(file => { println(s"Writing $file"); new FileOutputStream(file) })
    .getOrElse(System.out))

  val regex = """(\d+) (\d+) (\d+)"""r

  Source.fromInputStream(input)(Codec.UTF8)
    .getLines()
    .drop(1)
    .map { case regex(k, c, s) => Impl.clean(k.toInt, c.toInt, s.toInt) }
    .zipWithIndex
    .map {
      case (result, idx) => s"Case #${idx+1}: $result"
    }
    .foreach(line => output.write(line + "\n"))

  output.close()
}
