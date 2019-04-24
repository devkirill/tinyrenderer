package geom

interface Vec<N, T : Vec<N, T>> {
    operator fun plus(v: T): T
    operator fun minus(v: T): T
    operator fun times(t: Int): T
    operator fun times(t: Double): T
    fun length(): Double
}