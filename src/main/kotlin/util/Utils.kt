package util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.round

inline fun <T> T?.otherwise(block: () -> T?): T? = this ?: block()

// Heaps permutation algorithm in kotlin, borrowed from
// https://gist.github.com/dmdrummond/4b1d8a4f024183375f334a5f0a984718
fun <V> List<V>.permutations(): List<List<V>> {
    val retVal: MutableList<List<V>> = mutableListOf()

    fun generate(k: Int, list: List<V>) {
        // If only 1 element, just output the array
        if (k == 1) {
            retVal.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    Collections.swap(list, i, k - 1)
                } else {
                    Collections.swap(list, 0, k - 1)
                }
            }
        }
    }

    generate(this.count(), this.toList())
    return retVal
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun Double.roundDown(): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this).toDouble()
}