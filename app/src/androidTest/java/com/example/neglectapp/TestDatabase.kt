import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.neglectapp.data.room.network.SessionDao
import com.example.neglectapp.data.room.network.SessionDb
import com.example.neglectapp.domain.model.HeftosSession
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class TestDatabase {
    private lateinit var testDao: SessionDao
    private lateinit var db: SessionDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SessionDb::class.java).build()
        testDao = db.sessionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun addSessionToRoom() {
        val session = HeftosSession(1, LocalDateTime.now(), true, 0)
        testDao.addSession(session)
        val retrievedSession = testDao.getSession(session.id)
        assertEquals(session, retrievedSession)
    }
}