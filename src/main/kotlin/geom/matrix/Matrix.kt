package geom.matrix

abstract class Matrix<N, T : Matrix<N, T>> protected constructor(val sizeX: Int, val sizeY: Int, val content: MutableList<N>) {
    constructor(sizeX: Int, sizeY: Int, default: N) : this(sizeX, sizeY, createList(sizeX * sizeY, default))

    abstract fun copy(): T

    operator fun set(x: Int, y: Int, value: N) {
        content[x * sizeX + y] = value
    }

    operator fun get(x: Int, y: Int): N = content[x * sizeX + y]

    abstract operator fun plus(m: T): T
    abstract operator fun plusAssign(m: T)
    abstract operator fun minus(m: T): T
    abstract operator fun minusAssign(m: T)
    abstract operator fun times(m: T): T

    abstract fun transpose(): T

    companion object {
        fun <T> createList(size: Int, default: T): MutableList<T> {
            val content = mutableListOf<T>()
            repeat(size) { content.add(default) }
            return content
        }
    }
}