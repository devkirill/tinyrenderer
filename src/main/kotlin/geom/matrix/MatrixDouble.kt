package geom.matrix

class MatrixDouble private constructor(sizeX: Int, sizeY: Int, content: MutableList<Double>) : Matrix<Double, MatrixDouble>(sizeX, sizeY, content) {
    constructor(sizeX: Int, sizeY: Int) : this(sizeX, sizeY, createList(sizeX * sizeY, .0))

    override fun copy(): MatrixDouble {
        return MatrixDouble(sizeX, sizeY, content.toMutableList())
    }

    override fun plus(m: MatrixDouble): MatrixDouble {
        val a = copy()
        a += m
        return a
    }

    override fun plusAssign(m: MatrixDouble) {
        if (sizeX != m.sizeX || sizeY != m.sizeY)
            throw IllegalStateException()
        for (x in (0..sizeX))
            for (y in (0..sizeY))
                this[x, y] += m[x, y]
    }

    override fun minus(m: MatrixDouble): MatrixDouble {
        val a = copy()
        a -= m
        return a
    }

    override fun minusAssign(m: MatrixDouble) {
        if (sizeX != m.sizeX || sizeY != m.sizeY)
            throw IllegalStateException()
        for (x in (0..sizeX))
            for (y in (0..sizeY))
                this[x, y] -= m[x, y]
    }

    override fun times(m: MatrixDouble): MatrixDouble {
        if (sizeX != m.sizeY)
            throw IllegalStateException()
        val a = MatrixDouble(m.sizeX, sizeY)
        for (y in (0 until sizeY))
            for (x in (0 until m.sizeX)) {
                a[x, y] = (0 until sizeX).map { this[it, y] * m[x, it] }.sum()
            }
        return a
    }

    override fun transpose(): MatrixDouble {
        val a = MatrixDouble(sizeY, sizeX)
        for (x in 0 until sizeX)
            for (y in 0 until sizeY)
                a[y, x] = this[x, y]
        return a
    }
}