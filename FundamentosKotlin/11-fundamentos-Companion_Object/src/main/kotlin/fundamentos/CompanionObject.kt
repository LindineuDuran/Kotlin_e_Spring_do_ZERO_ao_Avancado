package fundamentos

data class MinhaClasse(
    var nome: String,
    var endereco: String,
    var idade: Int
) {
    companion object {
        fun criarComValoresPadrao(): MinhaClasse {
            return MinhaClasse("Lindineu", "Rua Teste", 54)
        }

        fun criarAPartirdeSegundaClasse(segundaClasse: SegundaClasse): MinhaClasse {
            return MinhaClasse(segundaClasse.nome, segundaClasse.endereco, segundaClasse.idade)

        }
    }
}

data class SegundaClasse(
    var nome: String,
    var endereco: String,
    var idade: Int
) {
    fun criarComValoresPadrao(): SegundaClasse {
        return SegundaClasse("Lindineu", "Rua Teste", 54)
    }
}

fun main() {
    var segundaClasse = SegundaClasse("nome", "endereco", 10).criarComValoresPadrao()
    println(segundaClasse)

    var minhacLoader = MinhaClasse.criarAPartirdeSegundaClasse(segundaClasse)
    println(minhacLoader)
}