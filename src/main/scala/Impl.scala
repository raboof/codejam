import scala.annotation._

object Impl {
  def flips(stack: String): Int = {
    var current = '+'
    var flips = 0
    stack.reverse.foreach { pancake: Char =>
      if (pancake != current) {
        flips = flips + 1
        current = pancake
      }
    }
    flips
  }
}
