import com.rodrigos01.fakoin.factory
import com.rodrigos01.fakoin.get
import com.rodrigos01.fakoin.inject
import data.AdultTalker
import data.Booze
import data.Corote
import data.Talker
import org.junit.Assert.assertEquals
import org.junit.Test

internal class FactoryTest {

    @Test
    fun shouldCreateNewInstanceEveryTime() {

        factory { Corote() as Booze }

        factory {
            AdultTalker(get()) as Talker
        }

        val talker: Talker by inject()
        val otherTalker: Talker by inject()

        assertEquals("Bullshit Entortando o Nêgo x1", talker.talk())

        assertEquals("Bullshit Entortando o Nêgo x1", otherTalker.talk())


    }

}