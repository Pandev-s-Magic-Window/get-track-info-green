package app.pandev.mw.get_track_info_green.json

import app.pandev.mw.get_track_info_green.track_info.TrackInfo

data class OkJsonResponse(
  val status: String,
  val request_id: String?,
  val data: TrackInfo
)
