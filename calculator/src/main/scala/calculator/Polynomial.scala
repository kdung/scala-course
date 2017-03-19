package calculator
import Math._

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal(b() * b() - 4 * a() * c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal {
      if (delta() < 0) NoSignal()
      else if (delta() == 0) {
        val root: Double = 0 - b() / (2 * a())
        Set(root)
      } else {
        val root1: Double = (sqrt(delta()) - b()) / (2 * a())
        val root2: Double = (-sqrt(delta()) - b()) / (2 * a())
        Set(root1, root2)
      }
    }
  }
}
