package app.pandev.mw.get_track_info_green.track_info.graphql

import com.google.gson.annotations.SerializedName

data class TrackUnion(
  val id: String,
  val name: String,
  val uri: String,
  @SerializedName("albumOfTrack") val album: AlbumOfTrack,
  @SerializedName("firstArtist") val mainArtistList: Artists,
  @SerializedName("otherArtists") val otherArtistList: Artists
)
