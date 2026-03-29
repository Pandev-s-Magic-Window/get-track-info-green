package app.pandev.mw.get_track_info_green

import app.pandev.mw.get_track_info_green.green_process.GreenTrackInfoManager
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class App {
  private val logger: Logger = LoggerFactory.getLogger(App::class.java)

  constructor(
    includeExtraData: Boolean = false,
  ) {
    try {

      val tim = GreenTrackInfoManager(
        includeExtraData = includeExtraData
      )
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

        val requestId = parseRequestId(line)

        tim.outputTrackInfo(
          trackId = tim.getCurrentTrackId(),
          requestId
        )
      }
      scanner.close()
    } catch (e: Exception) {
      logger.error(e.toString(), e)
    }
  }

  private fun parseRequestId(line: String): String? {
    try {
      val parsedLine = Gson().fromJson(line, JsonElement::class.java)

      return parsedLine.asJsonObject.get("request_id").asString
    } catch (_: Exception) {
    }
    return null
  }
}
