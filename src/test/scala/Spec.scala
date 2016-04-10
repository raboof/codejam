import org.scalatest._
import org.scalatest.prop._
import scala.math._

class Spec extends WordSpec with Matchers
    with GeneratorDrivenPropertyChecks
    {
  import Impl._

  "The implementation" should {
    "work" in {
      clean(13, 12)


      idx(Seq(0, 1, 2), 4, 2) should be(6)
      idx(Seq(5), 3, 0) should be(5)
      clean(5, 1) should contain theSameElementsAs(Range(1, 6))
      clean(2, 3) should contain theSameElementsAs(Seq(3))
      clean(1, 1) should contain theSameElementsAs(Seq(1))
      clean(2, 1) should contain theSameElementsAs(Seq(1, 2))
      clean(3, 2) should contain theSameElementsAs(Seq(2, 7))
      clean(2, 2) should contain theSameElementsAs(Seq(2))
      clean(3, 3) should contain theSameElementsAs(Seq(6))

      makeArt("LGL", 1) should be("LGL")
      makeArt("LGL", 2) should be("LGLGGGLGL")
      makeArt("LGL", 3) should be("LGLGGGLGLGGGGGGGGGLGLGGGLGL")

      check("L", 1)
      check("GLLGLLG", 3)
      check("LL", 27)

      forAll { in: (Seq[Boolean], Int) =>
        if (in._1.size > 0) {
          val c = (abs(in._2) % 100) + 1
          if (pow(in._1.size, c) < 1000) {
            check(in._1.map(if (_) 'G' else 'L').mkString, c)
          }
        }
      }
    }

    def check(seq: String, complexity: Int) = {
      val art = makeArt(seq, complexity)
      val suspectsGold = clean(seq.length, complexity).map(idx => charAt(art, idx - 1)).contains('G')
      val hasGold = seq.contains('G')
      suspectsGold should be(hasGold)
    }

    def charAt(str: String, idx: Long): Char = {
      if (idx > Int.MaxValue) charAt(str.drop(Int.MaxValue), idx - Int.MaxValue)
      else str.charAt(idx.toInt)
    }

    def makeArt(seq: String, complexity: Int): String = {
      if (complexity == 1) seq
      else makeArt(seq, complexity - 1).flatMap {
        case 'G' => "G" * seq.length
        case 'L' => seq
      }
    }
  }
}
