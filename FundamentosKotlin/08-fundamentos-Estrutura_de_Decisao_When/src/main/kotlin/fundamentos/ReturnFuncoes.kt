package fundamentos
fun retornaNumeroPorExtenso(numero: Int): String{
    if(numero == 5) {return "cinco"} else if(numero == 6) { return "seis"}

    return "número desconhecido"
}

fun main() {
    println(retornaNumeroPorExtenso(5))
    println(retornaNumeroPorExtenso(6))
    println(retornaNumeroPorExtenso(3))
}