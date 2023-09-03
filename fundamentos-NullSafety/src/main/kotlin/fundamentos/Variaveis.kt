package fundamentos

class Variaveis
{
    lateinit var teste: String

    fun iniciaVariaveis() {
        teste = "Teste"
    }
}

fun main() {
    var nome = "Gustavo"
    val nomeVal = "Gustavo"

    nome = "Daniel"
    //nomeVal = "Daniel" //change to var, val cannot be reassigned

    val idade = 24
    println(idade)

    val teste: String
    teste = "Teste"

    var texto = "Texto"
}