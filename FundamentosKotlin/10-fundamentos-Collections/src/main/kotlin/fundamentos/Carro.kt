package fundamentos

class Carro(var cor: String, val anoFabricacao: Int, val dono: Dono) {}
data class Dono(var nome: String, var idade: Int) {}

fun main(){
    var carro = Carro("cinza", 2021, Dono("Lindineu", 54))
    println(carro.cor)
    println(carro.anoFabricacao)
    println(carro.dono)
    println(carro.dono.nome)
    println(carro.dono.idade)

    carro.cor = "branco"
    carro.dono.idade = 55
    println(carro.cor)
}