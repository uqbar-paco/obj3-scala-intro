package uqbar.paco.implicits.campeonato

import uqbar.paco.admuniv.acad.{Alumno, Materia}

object AdmUnivImplicits {
  implicit def materiaToString(m: Materia) = m.nombre
  implicit def alumnoToList(a: Alumno) = a.materiasAprobadas
}