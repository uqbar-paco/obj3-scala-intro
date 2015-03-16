package uqbar.paco.scala.intro

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BaseSpec extends FlatSpec with Matchers

class PepitaSpec extends BaseSpec {
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