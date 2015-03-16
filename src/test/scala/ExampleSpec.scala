import org.scalatest._

class ExampleSpec extends FlatSpec with Matchers {

  "Un test" should "correr dentro del IDE" in {
    1 should be (1)
  }

  it should "fallar dentro del IDE" in {
    1 should be (2)
  }
}