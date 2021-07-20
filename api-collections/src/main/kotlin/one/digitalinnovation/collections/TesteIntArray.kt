package one.digitalinnovation.collections

fun main() {
    val values = IntArray(5)

    values[0] = 1
    values[1] = 7
    values[2] = 6
    values[3] = 3
    values[4] = 2

    for (valor in values) {
        println(valor)
    }

    // Outra forma utilizando expressão lambda "forEach"
    println("-----------------------")
    values.forEach { valor ->
        println(valor)
    }

    // Outra forma utilizando os indices
    println("-----------------------")
    for (index in values.indices) {
        println(values[index])
    }

    // Outra forma utilizando a ordenação do .sort()
    println("-----------------------")
    values.sort()
    for (valor in values) {
        println(valor)
    }
}