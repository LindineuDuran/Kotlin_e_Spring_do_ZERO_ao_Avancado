package fundamentos

fun main() {
    dizOi(retornaNome(), 54)
    dizOi(idade = 61, nome = "Silvia")
    dizOi("Ana Sophia")
}

fun retornaNome(): String {
    return "Lindineu"
}

fun dizOi(nome: String, idade: Int = 20) {
    println("Oi ${nome}, parabéns pelos seus ${idade} anos")
}