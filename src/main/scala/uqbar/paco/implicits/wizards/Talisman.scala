package uqbar.paco.implicits.wizards
import PartialFunction._
import StatsBuilder._


class Human
class Commoner extends Human

trait Magic {
  def stats : Stats
}

class FireElement  extends  Magic {
  def stats = _ + 20.attack
}

trait Talisman[T <: Magic]{val source:T }

trait RingOfFire extends Talisman[FireElement] { val source = new FireElement }

trait Environment { def effectFor(any:{ def stats : Stats }):Stats}

class RainyDay extends Environment {
  def effectFor(any:WithStats) = any match {
    case something:FireElement => something.stats % 90.attack
    case otro => otro.stats
  }
}

class Normal extends Environment {
  def effectFor(any:WithStats) = any.stats
}

object TalismanPowers {
  implicit def getTalismanPowers[T<:Magic]
    (any: Talisman[T])
    (implicit env:Environment = new Normal): Action = {
      env.effectFor(any.source).apply(Action())
  }
}


case class Action(attack: Float = 0, resist: Float = 0, dodge: Float = 0) {

  def + = op((a,b)=>a+b)(_)
  def - = op((a,b)=>a-b)(_)
  def * = op((a,b)=>a*b)(_)
  def / = op((a,b)=>a/b)(_)


  def op(f:(Float,Float)=>Float)(otro:Action) = {
    val Action(i1, d1, c1) = this
    val Action(i2, d2, c2) = otro
    Action( f(i1,i2), f(d1,d2), f(c1,c2))
  }
}


object StatsBuilder {
  type Stats = (Action) => Action
  type WithStats = { def stats : Stats }

  implicit class StatsOperations(s:Stats){
    def %(a:Action):Stats = (target:Action) => {
      val act = s.apply(target)
      act*(a/Action(100,100,100))
    }
  }

  implicit class IntIsStat(n: Int) {
    def attack: Action = Action(attack = n)
    def resist: Action = Action(resist = n)
    def dodge: Action = Action(dodge = n)
  }
}
