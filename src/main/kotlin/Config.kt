package app.pandev.mw.get_track_info_green

import java.io.File
import java.lang.String.join

class Config {
  companion object {
    fun getAppDataPath(): String {
      val appDataPath = System.getenv("AppData")

      val pathSegments = listOf<String>(
        appDataPath, "app.pandev.mw.get-track-info-green"
      )

      return join(File.separator, pathSegments)
    }
  }
}
