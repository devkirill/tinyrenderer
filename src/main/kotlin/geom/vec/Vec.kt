package geom.vec

interface Vec<N, T : Vec<N, T>> {
    operator fun plus(v: T): T
    operator fun plusAssign(v: T)
    operator fun minus(v: T): T
    operator fun minusAssign(v: T)
    operator fun times(t: Int): T
    operator fun times(t: Double): T
    operator fun timesAssign(t: Int)
    operator fun timesAssign(t: Double)
    fun length(): Double
}