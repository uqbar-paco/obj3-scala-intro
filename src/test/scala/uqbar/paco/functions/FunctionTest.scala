package uqbar.paco.functions

import org.scalatest.{FlatSpec, Matchers}

class FunctionTest extends FlatSpec with Matchers {
  val doble = new IntFunction({ _ * 2})
  val masCinco = new IntFunction({ _ + 5})
  val minConOcho = new IntFunction(8.min)
  
  "una funcion" should "calcularse bien" in {
    doble(3) should equal (6)
    masCinco(3) should equal (8)
    doble(3 to 7).toList should equal(List(6,8,10,12,14))
    minConOcho(3) shouldEqual 3
    minConOcho(20) shouldEqual 8
  }

  "una funcion" should "combinarse bien" in {
    doble.max(masCinco)(3) shouldEqual 8
    doble.comp(masCinco)(3) shouldEqual 16
    doble.max(masCinco)(3 to 7).toList shouldEqual List(8,9,10,12,14)
    doble.comp(masCinco)(3 to 7).toList shouldEqual List(16,18,20,22,24)
  }

  val genDoble = new GenFunction[Int,Int]({ _ * 2})
  val longi = new GenFunction[String,Int]({_.size})
  val esPar = new GenFunction[Int,Boolean]({_ % 2 == 0})

  "las funciones genericas" should "portarse bien" in {
    genDoble.comp(longi)("Hola") shouldEqual 8
    esPar.comp(longi)("Ernesto") shouldBe false
    longi(List("rondamon","quico","chavo","chilindrina")) shouldEqual List(8,5,5,11)
  }

}