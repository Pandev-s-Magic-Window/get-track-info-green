package app.pandev.mw.get_track_info_green.green_process

import app.pandev.mw.get_track_info_green.track_info.TrackInfo
import app.pandev.mw.get_track_info_green.json.ErrorJsonResponse
import app.pandev.mw.get_track_info_green.json.OkJsonResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import de.labystudio.spotifyapi.open.OpenSpotifyAPI
import de.labystudio.spotifyapi.open.model.GraphQLOperation
import de.labystudio.spotifyapi.open.model.track.TrackResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.UnknownHostException

class GreenTrackInfoManager {
  private val logger: Logger = LoggerFactory.getLogger(GreenTrackInfoManager::class.java)

  private val gson = Gson()
  private val readApiKeysDocsMsg = "Please read the \"About API keys (TOTP secrets)\" section in the Github docs."

  private var localApi: GreenProcess? = null
  private var openApi: OpenSpotifyAPI? = null

  private var initialized: Boolean = false

  private var includeExtraData = false

  constructor(
    includeExtraData: Boolean = false,
  ) {

    this.includeExtraData = includeExtraData

    try {
      localApi = GreenProcess()

      val openApiKeys = GreenApiKeysManager.getKeys() ?: throw Exception("Empty API keys for Spotify")
      openApi = OpenSpotifyAPI(openApiKeys)

      initialized = true
    } catch (e: Exception) {
      outputErrorJsonResponse(e)
    }
  }

  fun isInitialized(): Boolean {
    return initialized && localApi != null && openApi != null
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
    trackId: String? = null
  ) {
    if (!initialized) {
      return
    }

    if (trackId.isNullOrEmpty()) {
      outputErrorJsonResponse("Could not find the track ID for the current track. Is Spotify currently running on your PC?")
      return
    }

    // Manually assert as not null
    val localApi = localApi
    val openApi = openApi
    if (localApi == null || openApi == null) {
      return
    }

    val trackInfo = getTrackInfoFromOpenApi(trackId) ?: return

    // Output the full data to stdout
    println(
      gson.toJson(
        OkJsonResponse(
          "ok",
          trackInfo
        )
      )
    )
  }

  private fun outputErrorJsonResponse(e: Exception, trackId: String? = null) {
    logger.error(e.toString(), e)

    val jsonResponse = ErrorJsonResponse(
      "error",
      e.toString(),
      trackId
    )

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

    val jsonOutput = gson.toJson(jsonResponse)
    println(jsonOutput)
  }


  private fun getTrackInfoFromOpenApi(trackId: String): TrackInfo? {
    try {
      val graphqlRequestVars = JsonObject()
      graphqlRequestVars.addProperty("uri", "spotify:track:$trackId")

      var graphqlRawTrackInfo =
        openApi?.requestGraphQL(GraphQLOperation.GET_TRACK, graphqlRequestVars, JsonElement::class.java)

      if (graphqlRawTrackInfo == null) {
        outputErrorJsonResponse("Empty data from the Spotify API request.")
        return null
      }

      graphqlRawTrackInfo = getTrueTrackInfoFromOpenApi(trackId, graphqlRawTrackInfo)

      val graphqlTrackInfo = gson
        .fromJson(graphqlRawTrackInfo, TrackResponse::class.java)

      if (graphqlTrackInfo == null) {
        outputErrorJsonResponse("Could not serialize data from the Spotify API request.")
        return null
      }

      return TrackInfo.create(
        trackId = trackId,
        rawJson = graphqlRawTrackInfo,
        deserializedJson = graphqlTrackInfo,
        includeExtraData = includeExtraData
      )

    } catch (_: UnknownHostException) {
      outputErrorJsonResponse("Failed to perform an HTTP request to Spotify. Unreachable host.")
    } catch (_: IOException) {
      outputErrorJsonResponse("Failed to perform an HTTP request to Spotify. $readApiKeysDocsMsg")
    }
    return null
  }

  private fun getTrueTrackInfoFromOpenApi(trackId: String, graphqlRawTrackInfo: JsonElement): JsonElement {
    try {
      val audioAssociations = graphqlRawTrackInfo.asJsonObject
        ?.get("data")?.asJsonObject
        ?.get("trackUnion")?.asJsonObject
        ?.get("associationsV3")?.asJsonObject
        ?.get("audioAssociations")?.asJsonObject
        ?.get("items")?.asJsonArray

      if (audioAssociations == null || audioAssociations.size() <= 0) {
        return graphqlRawTrackInfo
      }

      val associatedTrackUri = audioAssociations.firstOrNull()?.asJsonObject
        ?.get("trackAudio")?.asJsonObject
        ?.get("data")?.asJsonObject
        ?.get("uri")?.asString

      if (associatedTrackUri == null || associatedTrackUri.contains(trackId)) {
        return graphqlRawTrackInfo
      }

      // If we are here it means the original track was probably a video,
      // so we need to fetch the data for the actual track
      val newGraphqlRequestVars = JsonObject()
      newGraphqlRequestVars.addProperty("uri", associatedTrackUri)
      val newGraphqlRawTrackInfo =
        openApi?.requestGraphQL(GraphQLOperation.GET_TRACK, newGraphqlRequestVars, JsonElement::class.java)

      // If we can't find anything just default to the video data
      if (newGraphqlRawTrackInfo == null) {
        return graphqlRawTrackInfo
      }

      return newGraphqlRawTrackInfo

    } catch (_: UnknownHostException) {
      outputErrorJsonResponse("Failed to perform an HTTP request to Spotify. Unreachable host.")
    } catch (_: IOException) {
      outputErrorJsonResponse("Failed to perform an HTTP request to Spotify. $readApiKeysDocsMsg")
    }
    return graphqlRawTrackInfo
  }
}
