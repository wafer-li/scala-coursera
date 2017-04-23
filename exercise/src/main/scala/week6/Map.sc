val map = Map("1" -> 1)

val s = map get "1"

class Poly(terms0: Map[Int, Double]) {

  val terms: Map[Int, Double] = terms0 withDefaultValue 0.0

  def + (other: Poly): Poly =
    new Poly((other.terms foldLeft terms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    terms + ((exp, coeff + terms(exp)))
  }

  override def toString: String =
    (for {
      (exp, coeff) <- terms.toList.sorted.reverse
    } yield coeff + "x^" + exp) mkString " + "
}

val p1 = new Poly(Map(0 -> 5.0, 1 -> 3, 2 -> 4 ))
val p2 = new Poly(Map(0 -> 5.0, 1 -> 3, 2 -> 5))

p1 + p2
