package app.pandev.mw.get_track_info_green

import app.pandev.mw.get_track_info_green.green_process.GreenTrackInfoManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class App {
  private val logger: Logger = LoggerFactory.getLogger(App::class.java)

  constructor() {
    try {
      val tim = GreenTrackInfoManager()
      if (!tim.isInitialized()) {
        return
      }

      // Listen for input indefinitely.
      // Will return the current track on a new line input.
      val scanner = Scanner(System.`in`)
      while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.equals("exit", ignoreCase = true)) {
          break
        }
        tim.outputTrackInfo(
          true,
          tim.getCurrentTrackId()
        )
      }
      scanner.close()
    } catch (e: Exception) {
      logger.error(e.toString(), e)
    }
  }
}
