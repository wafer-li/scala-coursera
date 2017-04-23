import scala.io.Source

val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

val words = in.getLines().toList.
  filter(word => word forall(char => char.isLetter))

val mnem = Map (
  '2' -> "ABC",
  '3' -> "DEF",
  '4' -> "GHI",
  '5' -> "JKL",
  '6' -> "MNO",
  '7' -> "PQRS",
  '8' -> "TUV",
  '9' -> "WXYZ"
)


val charCode: Map[Char, Char] =
  for {
    (digit, str) <- mnem
    char <- str
  } yield char -> digit

def wordCode(word: String): String =
  word.toUpperCase map charCode

wordCode("JAVA")

val wordsForNums: Map[String, Seq[String]] =
  words groupBy wordCode withDefaultValue Seq()

def encode(num: String): Set[List[String]] =
  if (num.isEmpty) Set(List())
  else
    (for {
      split <- 1 to num.length
      word <- wordsForNums(num take split)
      rest <- encode(num drop split)
    } yield word :: rest).toSet

encode("7225247368")

def translate(num: String): Set[String] =
  encode(num) map { _ mkString " " }

translate("7225247368")
