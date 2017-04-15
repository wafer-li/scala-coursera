abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat
  def successor = new Succ(this)
  def +(that: Nat): Nat
  def -(that: Nat): Nat
  def length: Int
}

object Zero extends Nat {
  def isZero: Boolean = true

  def predecessor: Nat =
    throw new Exception("Zero doesn't have a predecessor")

  def +(that: Nat): Nat = that

  def -(that: Nat): Nat =
    throw new Exception("A nat can't be negative")

  override def toString = "0"

  def length: Int = 0
}

class Succ(n: Nat) extends Nat {
  def isZero: Boolean = false

  def predecessor: Nat = n

  def +(that: Nat): Nat =
    if (that.isZero) this else n + that.successor

  def -(that: Nat): Nat =
    if (that.isZero) this else n - that.predecessor

  def length: Int =
    n.length + 1

  override def toString: String = this.length.toString
}

val one = new Succ(one)
val two = one + one
val four = two + two