package app.pandev.mw.get_track_info_green.green_process

import de.labystudio.spotifyapi.model.Track
import de.labystudio.spotifyapi.platform.linux.LinuxSpotifyApi
import de.labystudio.spotifyapi.platform.windows.api.spotify.SpotifyProcess
import org.apache.commons.lang3.SystemUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.OutputStream
import java.io.PrintStream

class GreenProcess {
  private val systemErrStream: PrintStream = System.err
  private val logger: Logger = LoggerFactory.getLogger(GreenTrackInfoManager::class.java)

  private var winSpotifyProcess: SpotifyProcess? = null
  private var linuxSpotifyProcess: LinuxSpotifyApi? = null

  constructor() {
    initSpotifyApis()
  }

  fun getCurrentTrackId(): String? {
    // Note: No current system API returns the full list of artists
    if (SystemUtils.IS_OS_WINDOWS) {
      return getCurrentTrackIdForWindows()
    } else if (SystemUtils.IS_OS_LINUX) {
      return getCurrentTrackIdForLinux()
    }
    return null
  }

  private fun getCurrentTrackIdForWindows(): String? {
    if (!SystemUtils.IS_OS_WINDOWS) {
      return null
    }

    try {
      if (!spotifyWindowsProcessIsRunning()) {
        initSpotifyWindowsProcess()
      }
      if (!spotifyWindowsProcessIsRunning()) {
        logger.error("Spotify.exe is not running!")
        return null
      }
    } catch (e: Exception) {
      logger.error(e.message, e)
      return null
    }

    val process = winSpotifyProcess
    if (process == null) {
      logger.error("Spotify.exe is not running!")
      return null
    }

    try {
      val trackId = process.readTrackId()
      if (!Track.isTrackIdValid(trackId)) {
        logger.error("Invalid track ID from memory: $trackId")
        return null
      }
      return trackId
    } catch (e: Exception) {
      logger.error(e.toString(), e)
    }
    return null
  }

  private fun getCurrentTrackIdForLinux(): String? {
    // TODO: Will complete when needed
    /*val process = linuxSpotifyProcess
    if (!SystemUtils.IS_OS_LINUX || process == null) {
      return null
    }
    try {
      return process.track?.id
    } catch (e: Exception) {
      logger.error(e.toString(), e)
    }*/
    return null
  }

  private fun initSpotifyApis() {
    // All the below APIs use e.printStackTrack,
    // so we need to suppress that by suppressing System.err
    if (SystemUtils.IS_OS_WINDOWS) {
      initSpotifyWindowsProcess()
    } else if (SystemUtils.IS_OS_LINUX) {
      suppressErrorMessages(true)
      linuxSpotifyProcess = LinuxSpotifyApi()
      suppressErrorMessages(false)
    }
  }

  private fun initSpotifyWindowsProcess() {
    try {
      if (spotifyWindowsProcessIsRunning()) {
        return
      }
      suppressErrorMessages(true)
      winSpotifyProcess = SpotifyProcess(null)
      suppressErrorMessages(false)
    } catch (e: Exception) {
      logger.error(e.message, e)
    }
  }

  private fun spotifyWindowsProcessIsRunning(): Boolean {
    try {
      val currentProcess = winSpotifyProcess ?: return false
      val currentProcessId = currentProcess.processId.toLong()
      val currentProcessHandle = ProcessHandle.of(currentProcessId)
      val currentProcessIsRunning = currentProcessHandle.isPresent && currentProcessHandle.get().isAlive
      return currentProcessIsRunning
    } catch (e: Exception) {
      logger.error(e.message, e)
    }
    return false
  }

  private fun suppressErrorMessages(suppress: Boolean) {
    if (!suppress) {
      System.setErr(this.systemErrStream)
      return
    }
    System.setErr(PrintStream(object : OutputStream() {
      override fun write(b: Int) {
      }
    }))
  }
}
