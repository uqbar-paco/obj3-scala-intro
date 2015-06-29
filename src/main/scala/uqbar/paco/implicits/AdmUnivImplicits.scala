package uqbar.paco.implicits

import uqbar.paco.admuniv.acad.Alumno
import uqbar.paco.admuniv.acad.Materia

object AdmUnivImplicits {
  implicit def materiaToString(m: Materia) = m.nombre
  implicit def alumnoToList(a: Alumno) = a.materiasAprobadas
}