import scala.annotation._
import scala.math._

object Impl {
  sealed trait Result
  case object Impossible extends Result {
    override val toString = "IMPOSSIBLE"
  }
  case class Possible(tiles: Seq[Long]) extends Result {
    override val toString = tiles.mkString(" ")
  }

  def clean(k: Int, complexity: Int, students: Long): Result = {
    val tiles = clean(k, complexity)
    if (tiles.size > students) Impossible
    else Possible(tiles)
  }

  def clean(k: Int, complexity: Int): Seq[Long] =
    split(Range(0, k).map(_.toLong), complexity).map(s => {
      val result = idx(s, k, complexity - 1) + 1
      println(s"$result touches $s")
      result
    })

  def idx(s: Seq[Long], k: Long, c: Int): Long =
    if (s.size > 0) { s.head.toLong * pow(k, c).toLong + idx(s.tail, k, c-1) }
    else 0

  def split(seq: Seq[Long], into: Int): Seq[Seq[Long]] = {
    if (seq.length > into) seq.slice(0, into) +: split(seq.drop(into), into)
    else Seq(seq)
  }
}
