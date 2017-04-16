trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(left, right) => left.eval + right.eval
  }

  def show: String = this match {
    case Number(n) => n.toString
    case Sum(left, right) => left.show + "+" + right.show
    case Prod(left, right) =>
      def f(e: Expr): String = e match {
        case Sum(l, r) => "(" + l.show + "+" + r.show + ")"
        case _ => e.show
      }

      f(left) + "*" + f(right)
    case Var(x) => x
  }

}

case class Number(n: Int) extends Expr

case class Sum(left: Expr, right: Expr) extends Expr

case class Var(x: String) extends Expr

case class Prod(left: Expr, right: Expr) extends Expr


Sum(Number(1), Number(2)).eval
Sum(Number(1), Number(2)).show

Prod(Sum(Number(1), Number(2)), Var("x")).show
Sum(Prod(Number(1), Number(2)), Var("x")).show
