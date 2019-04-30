package geom.matrix

class MatrixInt private constructor(sizeX: Int, sizeY: Int, content: MutableList<Int>) : Matrix<Int, MatrixInt>(sizeX, sizeY, content) {
    constructor(sizeX: Int, sizeY: Int) : this(sizeX, sizeY, createList(sizeX * sizeY, 0))

    override fun copy(): MatrixInt {
        return MatrixInt(sizeX, sizeY, content.toMutableList())
    }

    override fun plus(m: MatrixInt): MatrixInt {
        val a = copy()
        a += m
        return a
    }

    override fun plusAssign(m: MatrixInt) {
        if (sizeX != m.sizeX || sizeY != m.sizeY)
            throw IllegalStateException()
        for (x in (0..sizeX))
            for (y in (0..sizeY))
                this[x, y] += m[x, y]
    }

    override fun minus(m: MatrixInt): MatrixInt {
        val a = copy()
        a -= m
        return a
    }

    override fun minusAssign(m: MatrixInt) {
        if (sizeX != m.sizeX || sizeY != m.sizeY)
            throw IllegalStateException()
        for (x in (0..sizeX))
            for (y in (0..sizeY))
                this[x, y] -= m[x, y]
    }

    override fun times(m: MatrixInt): MatrixInt {
        if (sizeX != m.sizeY)
            throw IllegalStateException()
        val a = MatrixInt(m.sizeX, sizeY)
        for (y in (0 until sizeY))
            for (x in (0 until m.sizeX)) {
                a[x, y] = (0 until sizeX).map { this[it, y] * m[x, it] }.sum()
            }
        return a
    }

    override fun transpose(): MatrixInt {
        val a = MatrixInt(sizeY, sizeX)
        for (x in 0 until sizeX)
            for (y in 0 until sizeY)
                a[y, x] = this[x, y]
        return a
    }
}