package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def isMatch(chars: List[Char], state: Int): Int = {
      if (chars.isEmpty)
        state
      else if (state < 0)
        -1
      else if (chars.head == '(')
        isMatch(chars.tail, state + 1)
      else if (chars.head == ')')
        isMatch(chars.tail, state - 1)
      else
        isMatch(chars.tail, state)
    }

    isMatch(chars, 0) == 0
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
      1
    else if (coins.isEmpty || money < 0)
      0
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
}
