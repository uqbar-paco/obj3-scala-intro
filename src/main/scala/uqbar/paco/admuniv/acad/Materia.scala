package uqbar.paco.admuniv.acad

import scala.collection.mutable.Map
import scala.collection.immutable.List
import scala.Some

class Materia(val nombre: String) {
  val profes = Map[Int, String]()
  
  def laDio(anio: Int, profe: String) {
    profes += (anio -> profe)
  }
  
  def quienLaDio(anio: Int) = profes(anio)
  
  def apply() = nombre
  
  def apply(profe: String) = this.aniosEnQueLaDio(profe)
  
  def aniosEnQueLaDio(elProfe: String) = 
    profes.filter {x => x._2 == elProfe} .map {x => x._1}

  def aniosEnQueLaDio10(elProfe: String) = 
    profes.filter {_._2 == elProfe} .map {_._1}

  def aniosEnQueLaDio9(elProfe: String) = 
    profes.filter {case (anio,profe) => profe == elProfe} .map {_._1}

  def aniosEnQueLaDio2(elProfe: String) = { profes.foldLeft(List[Int]())  
    {(l,v) => if (v._2 == elProfe) v._1 :: l else l} 
  }

  def aniosEnQueLaDio4(elProfe: String) = { profes.foldLeft(List[Int]()) 
    {case (l , (anio,profe)) => if (profe == elProfe) anio :: l else l}
  }

  def aniosEnQueLaDio3(elProfe: String) = {
    profes.foldLeft(List[Int]()) (
        {(l,v) => val (anio,profe) = v ; if (profe == elProfe) anio :: l else l} 
    )
  }

  def aniosEnQueLaDio5(elProfe: String) = {
    profes.foldLeft(List[Int]()) (
        {(l,v) => v match {case (anio,profe) => if (profe == elProfe) anio :: l else l} }
    )
  }

  def aniosEnQueLaDio6(elProfe: String) = { profes.foldLeft(Iterable[Int]()) 
    {case (l , (anio,profe))  =>  (if (profe == elProfe) Some(anio) else None) ++ l}
  }

  def aniosEnQueLaDio7(elProfe: String) = { profes.foldLeft(Iterable[Int]()) 
    {case (l , (anio,profe))  =>  new Optionizer2[String,Int](elProfe,anio).ensureValue(profe) ++ l}
  }

  def aniosEnQueLaDio8(elProfe: String) = { profes.foldLeft(Iterable[Int]()) 
    {case (l , (anio,profe))  =>  (elProfe,anio).ensureValue(profe) ++ l}
  }

  def aniosEnQueLaDio11(elProfe: String) = { profes.foldLeft(Iterable[Int]()) 
    {case (l , (anio,profe))  =>  l ++ elProfe.ensureValue(profe,anio)}
  }

  implicit def pairToOptimizer2[T,U] (x : (T,U)): Optionizer2[T,U] = new Optionizer2(x._1, x._2)

  implicit def valueToOptimizer[T] (x : T): Optionizer[T] = new Optionizer(x)

}

class Optionizer[T](expected: T) {
  def ensure(actual: T) = if (actual == expected) Some(actual) else None
  def ensureValue[U](actual: T, value: U) = if (actual == expected) Some(value) else None
}

class Optionizer2[T,U](expected: T, value:U) {
  def ensureValue(actual: T) = if (actual == expected) Some(value) else None
}
