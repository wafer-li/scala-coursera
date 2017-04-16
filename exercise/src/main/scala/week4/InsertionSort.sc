def isort(xs: List[Int]): List[Int] = xs match {
  case Nil => List()
  case y :: ys => insert(y, isort(ys))
}

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => x :: List()
  case y :: ys => if (x < y) x :: xs else y :: insert(x , ys)
}

val list = List(4, 1, 3, 8)

isort(list)