package one.digitalinnovation.collections

fun main() {
    val idades = intArrayOf(10, 20, 21, 22, 30, 31, 40, 43, 50)
    val result = idades.filter {it > 31}
    println(result)
}