def queens(n: Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] =
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  placeQueens(n)
}

def isSafeMy(col: Int, queens: List[Int]): Boolean =
  (!(queens contains col)) && {
    if (queens.isEmpty) true
    else {
      queens.head != col - 1 &&
      queens.head != col + 1
    }
  }

// 不在对角线 = 行列差的绝对值不相等
def isSafe(col: Int, queens: List[Int]): Boolean = {
  val row = queens.length
  val queensWithRow = (row - 1 to 0 by -1) zip queens

  queensWithRow forall {
    case (r, c) =>
      c != col && math.abs(col - c) != row - r
  }
}

def show(queens: List[Int]) = {
  val lines =
    for {
      col <- queens
    } yield Vector.fill(queens.length)("*").updated(col, "X").mkString

  "\n" + lines.mkString("\n")
}

(queens(4) map show) mkString "\n"
