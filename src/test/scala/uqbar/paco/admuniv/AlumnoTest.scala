package uqbar.paco.admuniv

import org.scalatest.FlatSpec

import uqbar.paco.admuniv.acad.Materia
import uqbar.paco.admuniv.acad.Alumno
import uqbar.paco.admuniv.acad.Materia
import org.scalatest.Matchers

import uqbar.paco.implicits.AdmUnivImplicits._

class AlumnoTest extends FlatSpec with Matchers {
  val intro = new Materia("Intro")
  val orga = new Materia("Orga")
  val obj1 = new Materia("Obj1")
  val mate1 = new Materia("Mate1")
  val bd = new Materia("BD")
  val estr = new Materia("Estructuras")
  val ingles1 = new Materia("Ingles 1")
  
  
  val pepe = new Alumno("Pepe LePew")
  val ana = new Alumno("Ana NoDuerme")
  val roque = new Alumno("Roque Balboa")
  pepe.curso(intro, 2014, 7)
  pepe.curso(orga, 2014, 6)
  pepe.curso(mate1, 2014, 2)
  pepe.abandono(bd, 2014)
  pepe.estaCursando(bd, 2015)
  pepe.estaCursando(mate1, 2015)
  pepe.estaCursando(obj1, 2015)
  
  ana.curso(intro, 2014, 9)
  roque.curso(orga, 2014, 2)
  roque.curso(mate1, 2014, 5)
  roque.curso(ingles1, 2014, 8)
  roque.curso(obj1, 2014, 8)
  roque.curso(estr, 2015, 2)
  
  
  val funcionesAnio = List(pepe.cursoEnAnio(_:Int,intro), roque.cursoEnAnio(_:Int,estr))
//  val funcionesMat = List(pepe.cursoEn(2014,_:Materia), roque.cursoEn(2014,_:Materia))
//  val funcionesGente = List( 
//      { alu: Alumno => alu.cursoEn(2014,intro) }, 
//      { alu: Alumno => alu.cursoEn(2014,bd) } 
//   )
  val funcionesMat = Set(pepe,roque) map { a => a.cursoEnAnio(2014,_:Materia) }
  val funcionesGente = Set(intro,bd) map { m => { alu: Alumno => alu.cursoEnAnio(2014,m) } }
  
  "implicit definitions" should "apply if imported" in {
    intro.startsWith("I") shouldBe true
    orga.startsWith("R") shouldBe false
    roque.contains(mate1) shouldBe true
    roque.contains(orga) shouldBe false
  }
  
  "aplicacion parcial" should "no romperse" in {
    List(ana,roque).map { 
      pepe.materiasQueCursoCon(_).toSet 
    } shouldEqual List(Set(intro), Set(orga,mate1))
    
    funcionesAnio.map {_(2014)} shouldEqual List(true,false)  
    funcionesAnio exists {_(2014)} shouldBe true
    funcionesAnio forall {_(2014)} shouldBe false
    funcionesAnio forall {f => f(2014) || f(2015)} shouldBe true
    funcionesAnio exists {f => f(2014) && f(2015)} shouldBe false
    
    Set(intro,orga,bd,obj1,estr) filter {
      m => funcionesMat forall {_(m)}
    } shouldEqual Set(orga)
    Set(intro,orga,bd,obj1,estr) filter {
      m => funcionesMat exists {_(m)}
    } shouldEqual Set(intro,orga,bd,obj1)
    
    Set(roque,ana,pepe) filter {
      a => funcionesGente forall { _(a)} 
    } shouldEqual Set(pepe)
    Set(roque,ana,pepe) filter {
      a => funcionesGente exists { _(a)} 
    } shouldEqual Set(pepe,ana)
  }
}







