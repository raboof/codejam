import scala.annotation._

object Impl {
  def flips(stack: String): Int = {
    val (flips, _) = stack.reverse.foldLeft((0, '+')) {
      case ((flips, current), pancake) if pancake != current => (flips + 1, pancake)
      case (state, _) => state
    }
    flips
  }
}
