package one.digitalinnovation.collections

fun main() {
    val values = intArrayOf(2, 3, 4, 1, 10, 7)

    values.forEach {
        println(it)
    }

    println("----------------------")
    // Imprimindo de forma ordenada
    values.sort()
    values.forEach {
        println(it)
    }
}