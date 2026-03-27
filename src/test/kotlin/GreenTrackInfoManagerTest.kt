import app.pandev.mw.get_track_info_green.green_process.GreenTrackInfoManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

class GreenTrackInfoManagerTest {
  private val testStdout = ByteArrayOutputStream()
  private val originalStdout: PrintStream? = System.out

  @BeforeEach
  fun setUpStreams() {
    System.setOut(PrintStream(testStdout))
  }

  @Test
  fun `can get track id from system`() {
    assertDoesNotThrow {
      val gtim = GreenTrackInfoManager()
      assertTrue { gtim.isInitialized() }

      // Just check for exceptions
      gtim.getCurrentTrackId()
    }
  }

  @Test
  fun `can fetch track data from spotify's live api`() {
    assertDoesNotThrow {
      val gtim = GreenTrackInfoManager()
      assertTrue { gtim.isInitialized() }

      val trackId = "2FUf1dlkvcgKOiKpMIOl2j"
      gtim.outputTrackInfo(
        trackId = trackId
      )

      assertTrue {
        testStdout
          .toString()
          .contains("Orion (Live)")
      }
    }
  }

  @AfterEach
  fun restoreStreams() {
    System.setOut(originalStdout)
  }
}
