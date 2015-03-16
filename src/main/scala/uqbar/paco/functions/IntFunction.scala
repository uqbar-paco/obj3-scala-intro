package uqbar.paco.functions

class IntFunction(val theFunction: Int => Int) {
  def apply(n: Int) = theFunction(n)
  def apply(ns: Iterable[Int]) = ns map { theFunction(_) }
  def max(f: IntFunction) = new IntFunction( { n => this(n).max(f(n)) } )
  def comp(g: IntFunction) = new IntFunction( {n => this(g(n)) } )
}

class GenFunction[A,B](val theFunction: A => B) {
  def apply(n: A) = theFunction(n)
  def apply(ns: Iterable[A]) = ns map { theFunction(_) }
  def comp[C](g: GenFunction[C,A]) = new GenFunction[C,B]( {n => this(g(n)) } )
}