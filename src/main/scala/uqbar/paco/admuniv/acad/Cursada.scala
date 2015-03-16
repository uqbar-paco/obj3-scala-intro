package uqbar.paco.admuniv.acad

class Cursada ( val alumno: Alumno, val materia: Materia, val anio: Int )
{
  var estado: EstadoCursada = new CursadaActual()

  def resultado = estado.resultado
  def nota = estado.nota  
  def terminada = estado.terminada
  def abandono = resultado == ResultadoCursada.Abandonada
  def aprobo = resultado == ResultadoCursada.Aprobada
  def seAbandona { estado = new CursadaAbandonada() }
  def seTermina(nota: Int) { estado = new CursadaTerminada(nota) }
  def profesor = materia.quienLaDio(anio)
}

object ResultadoCursada extends Enumeration {
  val EnCurso = Value("Cursando")
  val Abandonada = Value("Abandono")
  val Aprobada = Value("Aprobo")
  val Desaprobada = Value("Desaprobo")
}

abstract class EstadoCursada {
  def resultado: ResultadoCursada.Value
  def nota: Int  
  def terminada: Boolean
}

class CursadaActual extends EstadoCursada {
  def resultado = ResultadoCursada.EnCurso
  def nota = { throw new Error("No se puede obtener la nota de una cursada no terminada") }  
  def terminada = false
}

class CursadaAbandonada extends EstadoCursada {
  def resultado = ResultadoCursada.Abandonada
  def nota = { throw new Error("No se puede obtener la nota de una cursada no terminada") }  
  def terminada = false
}

class CursadaTerminada(val nota: Int) extends EstadoCursada {
  def resultado = if (nota >= 4) ResultadoCursada.Aprobada else ResultadoCursada.Desaprobada 
  def terminada = true
}