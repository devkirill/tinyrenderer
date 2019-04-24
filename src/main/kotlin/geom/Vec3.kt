package geom

interface Vec3<N, T : Vec3<N, T>> : Vec<N, T> {
    fun cross(v: T): T
    fun scalar(v: T): N
    fun normalize(): Vec3d
}