package app.pandev.mw.get_track_info_green.core.json

data class ErrorJsonResponse(
  val status: String,
  val error_message: String,
  val track_id: String? = null
)
