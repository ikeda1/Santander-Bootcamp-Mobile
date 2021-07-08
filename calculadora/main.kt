fun main() {
    // Define fim do programa
	var loop = true
	while (loop)
	{
		menu()
		
		// Recebe a opção de operação
		var op = getInt()

		when (op)
		{
			0 -> loop = false

			1 -> {  // Recebe o primeiro valor
					var n1 = getFloat(1)
					// Recebe o segundo valor
					var n2 = getFloat(2)
					
					if (n1 == null || n2 == null) println("\nNúmero inválido! O valor não deve ser nulo")
					else {
					println("\nOperação: Soma")
					println("\nResultado: " + sum(n1, n2))}
				}
			
			2 -> {  // Recebe o primeiro valor
					var n1 = getFloat(1)
					// Recebe o segundo valor
					var n2 = getFloat(2)
					
					if (n1 == null || n2 == null) println("\nNúmero inválido! O valor não deve ser nulo")
					else {
					println("\nOperação: Subtração")
					println("\nResultado: " + sub(n1, n2))}
				}
			
			3 -> {  // Recebe o primeiro valor
					var n1 = getFloat(1)
					// Recebe o segundo valor
					var n2 = getFloat(2)
					
					if (n1 == null || n2 == null) println("\nNúmero inválido! O valor não deve ser nulo")
					else {
					println("\nOperação: Multiplicação")
					println("\nResultado: " + mult(n1, n2))}
				}
			
			4 -> {  // Recebe o primeiro valor
					var n1 = getFloat(1)
					// Recebe o segundo valor
					var n2 = getFloat(2)
					
					if (n1 == null || n2 == null) println("\nNúmero inválido! O valor não deve ser nulo")
					else {
					println("\nOperação: Divisão")
					println("Resultado: " + div(n1, n2))}
				}
			
			5 -> {  // Recebe o primeiro valor
					var n1 = getFloat(1)
					// Recebe o segundo valor
					print("\nValor #2 (Apenas inteiros): ")
					var n2 = getInt()
					
					if (n1 == null || n2 == null) println("\nNúmero inválido! O valor não deve ser nulo")
					else {
					println("\nOperação: Potenciação")
					println("Resultado: " + pow(n1, n2))}
				}
			
			6 -> {  println("\nOperação: Fatorial")
					print("\nValor #1 (Apenas inteiros): ")
					var n1 = getInt()
					if (n1 == null) println("Número inválido! O valor não deve ser nulo")
					else {
					println("Resultado: " + fact(n1))}
				}
					
			
			else -> println("\nOpção inválida! Digite um valor entre 0 ~ 6")
		}
	}
	
}

// Imprime o menu de opções
fun menu() 
{
    println("\n=============================")
    println("Escolha uma operação:\n")
	println("[0] Sair")
    println("[1] Soma")
    println("[2] Subtração")
    println("[3] Multiplicação")
    println("[4] Divisão")
    println("[5] Potenciação")
    println("[6] Fatorial")
    println("=============================")
	print("\nOpção: ")
}


// Recebe input do teclado de um valor decimal
fun getFloat(n:Int):Float?
{
	print("\nValor #$n: ")
    return readLine()?.toFloatOrNull()
}

// Recebe input do teclado de um valor inteiro
fun getInt():Int?
{
	return readLine()?.toIntOrNull()
}

// Soma
fun sum(n1:Float, n2:Float):Float
{
	return n1+n2
}

// Subtração
fun sub(n1:Float, n2:Float):Float
{ 
	return n1-n2
}

// Multiplicação
fun mult(n1:Float, n2:Float):Float
{
	return n1*n2
}

// Divisão
fun div(n1:Float, n2:Float):Float 
{
	return n1/n2
}

// Potenciação
fun pow(n1:Float, n2:Int?): Double
{
	return Math.pow(n1!!.toDouble(), n2!!.toDouble())
}

// Fatoração
fun fact(n1: Int): Long {
    var result = 1L
    for (i in 2..n1) result *= i
    return result
}