class Golondrina(energia: Int) {
  override def toString: String = "Yo soy una golondrina con " + energia + " de energia"
}

object Main extends App {
  println("Esto sale por la consola")

  val pepita = new Golondrina(150)
  println(pepita)
}
