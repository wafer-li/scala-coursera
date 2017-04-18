def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("Empty List")
  case List(_) => Nil
  case y :: ys => y :: init(ys)
}

init(List(0,1,2))

def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

concat(List(0, 1), List(2, 3))
concat(List(2), List())
concat(List(), List())
concat(List(), List(1))

// Use ++ for best practice
// quadratic!
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => List()
  case y :: ys => reverse(ys) ++ List(y)
}

reverse(List(0,1,2,3,4,5,6,7,8))

def removeAt[T](n: Int, xs: List[T]): List[T] = (xs take n) ++ (xs drop n + 1)

removeAt(1, List(0, 1, 2, 3))
