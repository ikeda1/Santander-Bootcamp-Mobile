package one.digitalinnovation.collections

fun main() {
    val nomes = Array<String>(3) {""}
    nomes[0] = "Beatriz"
    nomes[1] = "Pedro"
    nomes[2] = "João"

    for (nome in nomes) {
        println(nome)
    }

    println("------------------")
    nomes.sort()
    nomes.forEach { println(it) }

    println("------------------")
    val nomes2 = arrayOf("Beatriz", "Pedro", "João")
    nomes2.sort()
    nomes2.forEach { println(it) }
}