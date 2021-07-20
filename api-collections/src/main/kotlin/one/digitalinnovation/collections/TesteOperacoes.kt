package one.digitalinnovation.collections

fun main() {
    val salarios = doubleArrayOf(1000.0, 2250.0, 4000.0)

    for (salario in salarios) {
        println(salario)
    }

    println("----------------------------------------")
    println("Maior salario: ${salarios.maxOrNull()}")
    println("Menor salario: ${salarios.minOrNull()}")
    println("Media salarial: ${salarios.average()}")

    val salariosMaiorQue2500 = salarios.filter {it > 2500.0}
    println("----------------------------------------")
    salariosMaiorQue2500.forEach {println(it)}

    println("----------------------------------------")
    // Imprime quantidade de valores no intervalo de 2000 a 5000
    println(salarios.count { it in 2000.0..5000.0 })

    println("----------------------------------------")
    // Imprime se encontrar o valor exato passado no find
    println(salarios.find {it == 2250.0})
    println(salarios.find {it == 500.0})

    println("----------------------------------------")
    // Imprime valor booleano true ou false conforme a expressao
    println(salarios.any {it == 1000.0})
    println(salarios.any {it == 50.0})
}

