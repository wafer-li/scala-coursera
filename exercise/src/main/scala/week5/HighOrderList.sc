def squareListPatternMatching(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => y * y :: squareListPatternMatching(ys)
}

def squareListMap(xs: List[Int]): List[Int] =
  xs map (x => x * x)

squareListMap(List(1, 2, 3))
squareListPatternMatching(List(1, 2, 3))

squareListMap(List())
squareListPatternMatching(List())

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: _ =>
    val (head, tail) = xs span (y => y == x)
    head :: pack(tail)
}


val data = List("a", "a", "a", "b", "c", "c", "a")

def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs).map(x => (x.head, x.size))

encode(data)
encode(List())
