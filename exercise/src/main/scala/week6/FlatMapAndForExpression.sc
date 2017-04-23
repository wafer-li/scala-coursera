def isPrime(n: Int): Boolean =
  if (n < 1) false
  else (2 until n) forall { d => n % d != 0 }

isPrime(13)

def findPair(n: Int): Seq[(Int, Int)] =
  1 until n flatMap { i =>
    1 until i map { j =>
      (i, j)
    }
  } filter { case (a, b) => isPrime(a + b)}

findPair(7)

def scalarProduct(xs: Vector[Int], ys: Vector[Int]): Double =
  (for {
    (x, y) <- xs zip ys
  } yield x * y ).sum

scalarProduct(Vector(1, 2), Vector(3, 4))


