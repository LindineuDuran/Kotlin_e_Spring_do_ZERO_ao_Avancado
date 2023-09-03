package fundamentos

class Pessoa(private var nome: String, private var idade: Int) {
    override fun toString(): String {
        return "Classe: Pessoa. Nome: ${nome}, Idade: $idade"
    }
}

fun main()
{
    val lindineu = Pessoa("Lindineu Duran", 54)
    println(lindineu)
}