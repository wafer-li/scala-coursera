def mergesortInts(xs: List[Int]): List[Int] = {

  val n = xs.length / 2
  if (n == 0) xs

  else {
    def merge(xs: List[Int], ys: List[Int]): List[Int] =
      (xs, ys) match {
        case (Nil, yys) => yys
        case (xxs, Nil) => xxs
        case (x :: xxs, y :: yys) =>
          if (x < y) x :: merge(xxs, ys)
          else y :: merge(xs, yys)
      }

    val (first, second) = xs splitAt n
    merge(mergesortInts(first), mergesortInts(second))
  }
}

mergesortInts(List(4,5,2,1))
