package data

class Corote : Booze {

    private var count = 1

    override fun makeDrunk(): String {
        val current = count
        count++
        return "Entortando o Nêgo x$current"
    }
}