package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  println(contains(singletonSet(1), 0))
  printSet(singletonSet(1))
  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  printSet(union(s1, s2))

  val s = Set(2, 6, 4, 8)
  val y = Set(1000, 2, -1001, 7, 4, 8, -2001)
  def p = (a: Int) => a % 2 == 0
  printSet(intersect(s, p))

  printSet(map(s, x => x + 1))
  printSet(map(s, x => x * x))
}
