package app.pandev.mw.get_track_info_green.track_info

import com.google.gson.JsonElement
import de.labystudio.spotifyapi.open.model.track.TrackResponse

data class TrackInfo(
  val track_id: String,
  val track_name: String,
  val track_duration_ms: Int,
  val track_is_explicit: Boolean,

  val album_id: String,
  val album_name: String,

  val artist_name: String,
  val artist_full_name: String,

  var extra_data: JsonElement?
) {
  companion object {

    fun create(
        trackId: String,
        rawJson: JsonElement,
        deserializedJson: TrackResponse,
        includeExtraData: Boolean = false
    ): TrackInfo {
      val unknownArtistName = "UNKNOWN_ARTIST_NAME"

      val artistFullList = mutableListOf<String>()

      val mainArtist = deserializedJson.data?.trackUnion?.artists?.firstOrNull()

      var mainArtistName = unknownArtistName
      if (mainArtist != null) {
        mainArtistName = mainArtist.name
      }
      artistFullList.add(mainArtistName)

      val otherArtists = rawJson.asJsonObject
        ?.get("data")?.asJsonObject
        ?.get("trackUnion")?.asJsonObject
        ?.get("otherArtists")?.asJsonObject
        ?.get("items")?.asJsonArray

      otherArtists?.mapTo(artistFullList) {
        it.asJsonObject
          ?.get("profile")?.asJsonObject
          ?.get("name")?.asString ?: unknownArtistName
      }

      val extraData = if (includeExtraData) rawJson else null

      return TrackInfo(
        track_id = deserializedJson.data?.trackUnion?.id ?: trackId,
        track_name = deserializedJson.data?.trackUnion?.name ?: "UNKNOWN_TRACK_NAME",
        track_duration_ms = deserializedJson.data?.trackUnion?.duration?.durationMs ?: -1,
        track_is_explicit = deserializedJson.data?.trackUnion?.isExplicit ?: false,

        album_id = deserializedJson.data?.trackUnion?.album?.id ?: "UNKNOWN_ALBUM_ID",
        album_name = deserializedJson.data?.trackUnion?.album?.name ?: "UNKNOWN_ALBUM_NAME",

        artist_name = mainArtistName,
        artist_full_name = artistFullList.joinToString(", "),

        extra_data = extraData
      )
    }

  }
}
