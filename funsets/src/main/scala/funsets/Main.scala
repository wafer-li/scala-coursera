package funsets

object Main extends App {
  println(scala.runtime.BoxesRunTime.boxToBoolean(FunSets.contains(FunSets.singletonSet(1), 1)))
}
