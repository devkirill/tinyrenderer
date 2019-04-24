package utils

import geom.Vec3d
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.streams.toList

class WavefrontObj(val vertices: List<Vec3d>, val polygons: List<Polygon>) {

    class Polygon(val polygon: List<Int>) {
        fun size(): Int {
            return polygon.size
        }

        operator fun get(node: Int): Int {
            return polygon[node % polygon.size]
        }
    }

    companion object {
        fun parse(fileName: String): WavefrontObj {
            val file: BufferedReader
            try {
                file = BufferedReader(FileReader(fileName))
            } catch (e: IOException) {
                throw IllegalStateException(e)
            }

            val splitFileLines = file.lines()
                    .map { line -> line.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() } }
                    .toList()

            val vertices = splitFileLines
                    .filter { l -> l.isNotEmpty() && l[0] == "v" }
                    .map { l -> Vec3d(l[1].toDouble(), l[2].toDouble(), l[3].toDouble()) }
                    .toList()

            val polygons = splitFileLines
                    .filter { l -> l.isNotEmpty() && l[0] == "f" }
                    .map { l ->
                        l.drop(1)
                                .map { s -> Arrays.asList(*s.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())[0] }
                                .map { it.toInt() - 1 }
                    }
                    .map { Polygon(it) }
                    .toList()

            return WavefrontObj(vertices, polygons)
        }
    }
}
