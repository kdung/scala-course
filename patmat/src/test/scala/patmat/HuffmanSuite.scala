package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test(" times ") {
    val time = times(string2Chars("aba"))
    assert(time === List(('a', 2), ('b', 1)) || time === List(('b', 1), ('a', 2)) )
  }

  test(" singleton ") {
    new TestTrees {
      assert(singleton(t1::Nil))
      assert(!singleton(t2::(t1::Nil)))
    }
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode secret") {
    val secret: List[Bit] = List(1,0,0,0,1,0,1,0)
    val code: CodeTree = Fork(Leaf('A', 8),
      Fork(
        Fork(Leaf('B',3),
          Fork(Leaf('C',1),Leaf('D',1),List('C','D'),2), List('B','C','D'), 5),
        Fork(
          Fork(Leaf('E',1), Leaf('F',1),List('E','F'),2),
          Fork(Leaf('G',1), Leaf('H',1), List('G','H'),2),
          List('E','F','G','H'),4), List('B','C','D','E','F','G','H'),9),
      List('A','B','C','D','E','F','G','H'),17)
    assert(decode(code, secret) === List('B', 'A', 'C'))
    assert(decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
    println(quickEncode(code)(List('B', 'A', 'C')))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
