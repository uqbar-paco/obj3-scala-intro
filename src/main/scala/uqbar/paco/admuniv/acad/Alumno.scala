package uqbar.paco.admuniv.acad

import scala.collection.mutable.Set

class Alumno(var nombre: String) {
  var cursadas = List[Cursada]()
  
  def addToCursadas(c: Cursada) { cursadas = c :: cursadas }
  
  def estaCursando(m: Materia, anio: Int) { addToCursadas(new Cursada(this, m, anio)) }
  
  def curso(m: Materia, anio: Int, nota: Int) {
    val cursada = new Cursada(this, m, anio)
    cursada.seTermina(nota)
    addToCursadas(cursada)
  }

  def abandono(m: Materia, anio: Int) {
    val cursada = new Cursada(this, m, anio)
    cursada.seAbandona
    addToCursadas(cursada)
  }

  def cuantasVecesCurso(m: Materia) = cursadas filter {_.materia == m} size
  
  def cuantasVecesCursoFold(m: Materia) = 
      cursadas.foldLeft(0) {(n,cursada) => n + (if (cursada.materia == m) 1 else 0) }
  
  def cursadasTerminadas = cursadas.filter {_.terminada}
  
  def materiasAprobadas = cursadas.filter (_.aprobo) map {_.materia}
  
  def noBajaDe(nota: Int) = cursadasTerminadas forall {_.nota >= nota}
  
  def aniosConCursada = cursadas map {_.anio} toSet

  def cursoCon(profe: String) = cursadas exists {cursada => cursada.profesor == profe}
  
  def cursoEnAnio(anio: Int, m : Materia) = 
        cursadas exists { cursada => (cursada.materia == m) && (cursada.anio == anio) }
  
  def materiasQueCursoCon(a : Alumno) = 
        cursadas. 
          filter({ cursada => a.cursoEnAnio(cursada.anio, cursada.materia) }). 
          map({_.materia})
          
  def materiasConMejorNota = 
        cursadasTerminadas.foldLeft(0 -> List[Materia]()) { (actual,cursada) =>
          val (maxNota, materias) = actual
          if (cursada.nota > maxNota) { cursada.nota -> List(cursada.materia) }
          else if (cursada.nota == maxNota) { maxNota -> (cursada.materia :: materias) }
          else actual
        }

  def materiasConMejorNotaIterativo = {
    var maxNota = 0
    var materias = List[Materia]()
    cursadasTerminadas.foreach {cursada => 
      if (cursada.nota > maxNota) {
        maxNota = cursada.nota
        materias = List(cursada.materia) 
      } else if (cursada.nota == maxNota) {  
        materias = cursada.materia :: materias 
      }
    }
    materias
  } 
  
  def cursadasEnAnios(criterio: Int => Boolean) = 
        cursadas filter { cursada => criterio(cursada.anio) }

}