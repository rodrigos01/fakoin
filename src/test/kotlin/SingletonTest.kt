import com.rodrigos01.fakoin.factory
import com.rodrigos01.fakoin.get
import com.rodrigos01.fakoin.inject
import com.rodrigos01.fakoin.singleton
import data.AdultTalker
import data.Booze
import data.Corote
import data.Talker
import org.junit.Assert
import org.junit.Test

internal class SingletonTest {
    @Test
    fun shouldReuseSameInstance() {

        singleton { Corote() as Booze }

        factory {
            AdultTalker(get()) as Talker
        }

        val talker: Talker by inject()
        val otherTalker: Talker by inject()

        Assert.assertEquals("Bullshit Entortando o Nêgo x1", talker.talk())

        Assert.assertEquals("Bullshit Entortando o Nêgo x2", otherTalker.talk())


    }
}