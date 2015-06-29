package uqbar.paco.implicits

import org.scalatest.Matchers
import org.scalatest.FlatSpec

import uqbar.paco.implicits.CampeonatoImplicits._

class CampeonatoTest extends FlatSpec with Matchers {
  Campeonato.addToEquipos(new Equipo("Comu"))
  Campeonato.addToEquipos(new Equipo("Chaca"))
  Campeonato.addToEquipos(new Equipo("Arsenal"))
  Campeonato.addToEquipos(new Equipo("Bera"))
  Campeonato.addToEquipos(new Equipo("Quilmes"))
  Campeonato.addToEquipos(new Equipo("Lanus"))
  
  "equipos" should "exist when they should" in {
    Campeonato.hayEquipoLlamado("Bera") shouldBe true
    Campeonato.hayEquipoLlamado("Beraz") shouldBe false
  }
  
  "partidos" should "register properly" in {
    "Comu" leGanoDeLocalA "Chaca" resultado (3 a 1)
    "Bera" leGanoDeLocalA "Quilmes" resultado (4 a 2)
    Campeonato.jugaron("Chaca", "Comu") shouldBe true
    Campeonato.jugaron("Chaca", "Bera") shouldBe false
  }

}