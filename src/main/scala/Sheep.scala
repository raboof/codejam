import scala.annotation._

object Sheep {
  def lastCall(n: Int): String = n match {
    case 0 => "INSOMNIA"
    case other => lastCall(other, 1, Range(0, 10)).toString
  }
  @tailrec
  private def lastCall(n: Int, i: Int, missing: Seq[Int]): Int = {
    val m = i * n
    missing diff digits(m) match {
      case Seq() => m
      case nonempty => lastCall(n, i+1, nonempty)
    }
  }
  private def digits(n: Int): Seq[Int] =
    if (n < 10) Seq(n)
    else (n % 10) +: digits(n / 10)
}

class Sheep(cases: Iterator[String]) {

}
