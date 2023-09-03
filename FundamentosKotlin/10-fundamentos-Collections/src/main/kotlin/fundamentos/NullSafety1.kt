package fundamentos

fun main() {
    var lista : List<Int?> = listOf(1, 2, null, 3)
    var listaNullable : List<Int?>? = null

    var nome1 : String? = null

    var tamanho: Int = nome1?.length ?: 0 //Elvis Operator "?:"

    println(tamanho)

    var nome2 : String? = "Lindineu"

    if(nome2 != null){
        println(nome2.length)
    }

    val toShort : Short = nome2!!.length.toShort()
    println(toShort)
}