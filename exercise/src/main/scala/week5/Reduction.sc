def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())((e, l) => f(e) :: l)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)((_, n) => n + 1)

mapFun(List(1, 2, 3, 4), (x: Int) => x * 2)
lengthFun(List(1,2,3,4))