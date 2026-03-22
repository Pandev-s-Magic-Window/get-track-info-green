package app.pandev.mw.get_track_info_green.green_process

import app.pandev.mw.get_track_info_green.core.json.ErrorJsonResponse
import app.pandev.mw.get_track_info_green.core.json.OkJsonResponse
import app.pandev.mw.get_track_info_green.track_info.graphql.TrackInfo
import app.pandev.mw.get_track_info_green.track_info.persistent_cache.PersistentTrackInfoCache
import app.pandev.mw.get_track_info_green.track_info.persistent_cache.PersistentTrackInfoCacheItem
import com.google.gson.Gson
import com.google.gson.JsonObject
import de.labystudio.spotifyapi.open.OpenSpotifyAPI
import de.labystudio.spotifyapi.open.model.GraphQLOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.UnknownHostException

class GreenTrackInfoManager {
  private val logger: Logger = LoggerFactory.getLogger(GreenTrackInfoManager::class.java)

  private var trackInfoCache: PersistentTrackInfoCache? = null
  private var localApi: GreenProcess? = null
  private var openApi: OpenSpotifyAPI? = null

  private var initialized: Boolean = false

  constructor() {
    try {
      trackInfoCache = PersistentTrackInfoCache()
      localApi = GreenProcess()

      val openApiKeys = GreenApiKeysManager.getKeys() ?: throw Exception("Empty API keys for Spotify")
      openApi = OpenSpotifyAPI(openApiKeys)

      initialized = true
    } catch (e: Exception) {
      outputErrorJsonResponse(e)
    }
  }

  fun isInitialized(): Boolean {
    return initialized && trackInfoCache != null && localApi != null && openApi != null
  }

  fun getCurrentTrackId(): String? {
    if (!initialized) {
      return null
    }

    try {
      return localApi?.getCurrentTrackId()
    } catch (e: Exception) {
      outputErrorJsonResponse(e)
    }
    return null
  }

  fun outputTrackInfo(
    useCache: Boolean,
    trackId: String?
  ) {
    if (!initialized) {
      return
    }

    if (trackId.isNullOrEmpty()) {
      outputErrorJsonResponse("Could not find the track ID for the current track. Is Spotify currently running in your PC?")
      return
    }

    // Manually assert as not null
    val trackInfoCache = trackInfoCache
    val localApi = localApi
    val openApi = openApi
    if (trackInfoCache == null || localApi == null || openApi == null) {
      return
    }

    try {
      if (useCache) {
        val cachedTrackInfoItem: PersistentTrackInfoCacheItem? = trackInfoCache.get(trackId)
        if (cachedTrackInfoItem != null) {
          outputOkJsonResponseFromTrackInfoCacheItem(cachedTrackInfoItem)
          return
        }
      }
    } catch (e: Exception) {
      outputErrorJsonResponse(e, trackId)
    }

    val outputJson = getOutputJsonStringFromOpenApi(trackId)
    try {
      if (useCache && outputJson != null) {
        trackInfoCache.add(PersistentTrackInfoCacheItem(trackId, outputJson))
      }
    } catch (e: Exception) {
      outputErrorJsonResponse(e, trackId)
    }
  }

  private fun outputOkJsonResponseFromTrackInfo(trackInfo: TrackInfo): String {
    val jsonResponse = OkJsonResponse(
      "ok",
      trackInfo.data.trackUnion.id,
      trackInfo.data.trackUnion
    )

    val gson = Gson()
    val jsonOutput = gson.toJson(jsonResponse)
    println(jsonOutput)

    return jsonOutput
  }

  private fun outputOkJsonResponseFromTrackInfoCacheItem(trackInfo: PersistentTrackInfoCacheItem) {
    println(trackInfo.json_data)
  }

  private fun outputErrorJsonResponse(e: Exception, trackId: String? = null) {
    logger.error(e.toString(), e)

    val jsonResponse = ErrorJsonResponse(
      "error",
      e.toString(),
      trackId
    )

    val gson = Gson()
    val jsonOutput = gson.toJson(jsonResponse)
    println(jsonOutput)
  }

  private fun outputErrorJsonResponse(errorMessage: String, trackId: String? = null) {
    logger.error(errorMessage)

    val jsonResponse = ErrorJsonResponse(
      "error",
      errorMessage,
      trackId
    )

    val gson = Gson()
    val jsonOutput = gson.toJson(jsonResponse)
    println(jsonOutput)
  }


  private fun getOutputJsonStringFromOpenApi(trackId: String): String? {
    val readApiKeysDocsMsg = "Please read the \"About API keys (TOTP secrets)\" section in the Github docs."
    try {
      val variables = JsonObject()
      variables.addProperty("uri", "spotify:track:$trackId")
      val trackInfo = openApi?.requestGraphQL(GraphQLOperation.GET_TRACK, variables, TrackInfo::class.java)
      if (trackInfo == null) {
        outputErrorJsonResponse("Empty data from the Spotify API request.")
        return null
      }
      return outputOkJsonResponseFromTrackInfo(trackInfo)
    } catch (_: UnknownHostException) {
      outputErrorJsonResponse("Failed to perform an HTTP request to Spotify. Unreachable host.")
    } catch (_: IOException) {
      outputErrorJsonResponse("Failed to perform an HTTP request to Spotify. $readApiKeysDocsMsg")
    }
    return null
  }
}
