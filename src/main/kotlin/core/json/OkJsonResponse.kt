package app.pandev.mw.get_track_info_green.core.json

import app.pandev.mw.get_track_info_green.track_info.graphql.TrackUnion

data class OkJsonResponse(
  val status: String,
  val track_id: String,
  val data: TrackUnion
)
