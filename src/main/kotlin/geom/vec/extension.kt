package geom.vec

fun toInt(d: Double): Int = (d + 1e-9).toInt()

fun <N, T : Vec3<N, T>> cross(v1: T, v2: T): T = v1.cross(v2)
fun <N, T : Vec3<N, T>> scalar(v1: T, v2: T): N = v1.scalar(v2)