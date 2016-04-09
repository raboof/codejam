import org.scalatest._

class Spec extends WordSpec with Matchers {
  import Impl._

  "The implementation" should {
    "produce the right result" in {
      flips("-") should be (1)
      flips("-+") should be(1)
      flips("+-") should be(2)
      flips("+++") should be(0)
      flips("--+-") should be(3)
    }
  }
}
