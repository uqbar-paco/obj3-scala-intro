package uqbar.paco.implicits.wizards

import org.scalatest.{FlatSpec, Matchers}
import StatsBuilder._
import TalismanPowers._

class TalismanSpec extends FlatSpec with Matchers {

  val commonerWithRing  = new Commoner with RingOfFire

  "commoner with fire talisman" should "get fire attack bonus" in {
    commonerWithRing.attack should be(20)
  }

  "commoner with fire talisman in a rainny day" should "get only 90% of fire bonus" in {
    implicit val day = new RainyDay
    commonerWithRing.attack should be(18)
  }

}
