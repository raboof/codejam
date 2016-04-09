import org.scalatest._

class SheepSpec extends WordSpec with Matchers {
  "Sheep" should {
    "produce the right result" in {
      Sheep.lastCall(0) should be ("INSOMNIA")
      Sheep.lastCall(1) should be("10")
      Sheep.lastCall(2) should be("90")
      Sheep.lastCall(11) should be("110")
      Sheep.lastCall(1692) should be("5076")
    }
  }
}
