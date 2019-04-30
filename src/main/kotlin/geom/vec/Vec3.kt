package geom.vec

interface Vec3<N, T : Vec3<N, T>> : Vec<N, T> {
    fun times(v: T): T
    fun cross(v: T): T
    fun scalar(v: T): N
    fun normalize(): Vec3d
}