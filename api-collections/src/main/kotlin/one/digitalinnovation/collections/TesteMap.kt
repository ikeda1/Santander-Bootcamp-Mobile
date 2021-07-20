package one.digitalinnovation.collections

fun main() {
    // map utilizando o pair
    val pair: Pair<String, Double> = Pair("Joao", 1000.0)
    val map1 = mapOf(pair)

    map1.forEach { (k, v) -> println("Chave: $k - Valor: $v")}

    // map utilizando a sintaxe infix
    val map2 = mapOf(
        "Pedro" to 2500.0,
        "Beatriz" to 3000.0
    )
    map2.forEach { (k, v) -> println("Chave: $k - Valor: $v")}
}