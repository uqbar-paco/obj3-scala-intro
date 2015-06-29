package uqbar.paco.implicits.campeonato

class Equipo(val nombre: String) {
  def leGanoDeLocalA(vis: Equipo) = {
    new PartidoLocalBuilder(this,vis)
  }
}

class Partido(val local: Equipo, val golesLocal: Int, val visitante: Equipo, val golesVisitante: Int) {
  def jugo(e: Equipo) = e == local || e == visitante
}

class PartidoLocalBuilder(val local: Equipo, val visitante: Equipo) {
  def resultado(resu: Resultado) {
    Campeonato.addToPartidos(new Partido(local, resu.goles1, visitante, resu.goles2))    
  }
}

class Resultado(val goles1: Int) {
  var goles2: Int = 0
  
  def a(goles: Int) = {
    goles2 = goles
    this
  }
}

object Campeonato {
  var equipos = List[Equipo]()
  var partidos = List[Partido]()
  
  def addToEquipos(e: Equipo) {equipos = equipos :+ e}
  def addToPartidos(p: Partido) {partidos = partidos :+ p}
  
  def hayEquipoLlamado(nom: String) = equipos.exists { eq => eq.nombre == nom }
  def equipoLlamado(nom: String) = equipos.find { eq => eq.nombre == nom }.get
  
  def jugaron(e1: Equipo, e2: Equipo) = partidos.exists { p => p.jugo(e1) && p.jugo(e2) }
}


object CampeonatoImplicits {
  implicit def stringToEquipo(s: String) = Campeonato.equipoLlamado(s)
  implicit def intToResultado(goles: Int) = new Resultado(goles)
} 