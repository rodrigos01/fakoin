package data

class AdultTalker(val booze: Booze) : Talker {

    private val myBool
        get() = true

    override fun talk(): String {
        return "Bullshit ${booze.makeDrunk()}"
    }
}