package uqbar.paco.admuniv

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import uqbar.paco.admuniv.acad.Materia
import java.util.NoSuchElementException

class MateriaTest extends FlatSpec with ShouldMatchers {
  val intro = new Materia("Intro")
  val orga = new Materia("Orga")
  orga.laDio(2013, "Mara")
  orga.laDio(2014, "Mara")
  orga.laDio(2015, "Flavia")
  
  "un objeto" should "responder al apply sin parametros" in {
    intro.nombre should equal ("Intro")
    intro() should equal ("Intro")
  }
   
  "una materia" should "saber quien la dio cuando" in {
    an [NoSuchElementException] should be thrownBy intro.quienLaDio(2015)
    an [NoSuchElementException] should be thrownBy orga.quienLaDio(2012)
    orga.quienLaDio(2015) should equal ("Flavia")
  }

  "una materia" should "darme los anios para un profe" in {
    orga.aniosEnQueLaDio("Mara").toList.sorted should equal (List(2013,2014)) 
    orga.aniosEnQueLaDio2("Mara").toList.sorted should equal (List(2013,2014)) 
    orga.aniosEnQueLaDio3("Mara").toList.sorted should equal (List(2013,2014)) 
    orga.aniosEnQueLaDio4("Mara").toList.sorted should equal (List(2013,2014)) 
    orga.aniosEnQueLaDio5("Mara").toList.sorted should equal (List(2013,2014)) 
    orga.aniosEnQueLaDio6("Mara").toList.sorted should equal (List(2013,2014)) 
    orga.aniosEnQueLaDio11("Mara").toList.sorted should equal (List(2013,2014)) 
  }
  
//  "un option" should "portarse como un traversable" in {
//    Some(3).thisCollection
//  }
}