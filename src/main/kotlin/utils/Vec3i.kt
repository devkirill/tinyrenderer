package utils

data class Vec3i(val x: Int, val y: Int, val z: Int) {
    operator fun plus(p: Vec3i) = Vec3i(x + p.x, y + p.y, z + p.z)
    operator fun minus(p: Vec3i) = Vec3i(x - p.x, y - p.y, z - p.z)
    operator fun times(n: Int) = Vec3i(x * n, y * n, z * n)
    operator fun div(n: Int) = Vec3i(x / n, y / n, z / n)
}

data class Vec3d(val x: Double, val y: Double, val z: Double) {
    constructor(v: Vec3i) : this(v.x.toDouble(), v.y.toDouble(), v.z.toDouble())

    operator fun plus(p: Vec3d) = Vec3d(x + p.x, y + p.y, z + p.z)
    operator fun minus(p: Vec3d) = Vec3d(x - p.x, y - p.y, z - p.z)
    operator fun times(n: Double) = Vec3d(x * n, y * n, z * n)
    operator fun div(n: Double) = Vec3d(x / n, y / n, z / n)
}