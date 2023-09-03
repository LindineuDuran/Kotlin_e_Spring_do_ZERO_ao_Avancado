package fundamentos

fun main() {
    var lista = listOf(1, 2, 3, 4, 5, 6)
    val pares = lista.filter { it % 2 == 0 }
    println(pares)

    val primeiroPar = lista.filter { it % 2 == 0 }.first()
    println(primeiroPar)

    lista.forEach { println(it)}

    for(numero in lista) {println(numero)}

    println(lista[0])
    println(lista.get(0))
    println(lista.size)
    println(lista.indexOf(6))

    var lista1 = mutableListOf(1, 2, 3, 4, 5, 6)
    println("Lista: $lista1")

    lista1.add(8)
    println("Lista: $lista1")

    lista1.removeAt(0)
    println("Lista: $lista1")

    lista1.remove(3)
    println("Lista: $lista1")

    lista1[0] = 20
    println("Lista: $lista1")

    var lista2 = mutableListOf(4, 1, 2, 5, 6, 3)
    lista2.sort()
    println(lista2)

    lista2.shuffle()
    println("Lista: $lista2")

    var listaNomes = mutableListOf("Lindineu", "Silvia", "Ana Sophia")
    println(listaNomes)

    listaNomes.sort()
    println(listaNomes)

    var setNumeros = mutableSetOf(1, 2, 3, 2)
    println(setNumeros.contains(2))
    println(setNumeros)

    var mapNomeIdade = mutableMapOf("Lindineu" to 54, "Silvia" to 61)
    println(mapNomeIdade)

    mapNomeIdade["Ana Sophia"] = 19
    println(mapNomeIdade)

    mapNomeIdade.remove("Ana Sophia")
    println(mapNomeIdade)

    mapNomeIdade.putIfAbsent("Ana Sophia", 20)
    println(mapNomeIdade)
}