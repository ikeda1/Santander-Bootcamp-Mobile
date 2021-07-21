package one.digitalinnovation.collections

class Repositorio<T> {
    private val map = mutableMapOf<String, T>()

    // Cria um valor no map
    fun create(id: String, value: T) {
        map[id] = value
    }

    fun remove(id: String) = map.remove(id)

    // Procura e retorna o id procurado
    fun findById(id: String) = map[id]

    // // Procura e retorna todos os valores
    fun findAll() = map.values
}