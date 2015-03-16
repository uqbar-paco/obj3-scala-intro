import org.scalatest.{FlatSpec, Matchers}

class BaseSpec extends FlatSpec with Matchers

class ExampleSpec extends BaseSpec {
  "Una golondrina" should "perder energia al volar" in {
    val pepita = new Golondrina(100)
    pepita.energia should be (100)

    pepita.volar(75)
    pepita.energia should be (25)
  }

  it should "saber sus viajes largos" in {
    val pepita = new Golondrina(100)
    pepita.volar(75)
    pepita.volar(50)

    pepita.viajesLargos should be (List(75))
  }
}