package app.pandev.mw.get_track_info_green.native_image

import app.pandev.mw.get_track_info_green.green_process.GreenTrackInfoManager

class MappingGenerator {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val tim = GreenTrackInfoManager()
      if (!tim.isInitialized()) {
        return
      }

      var trackId = tim.getCurrentTrackId()
      if (trackId == null) {
        trackId = "2FUf1dlkvcgKOiKpMIOl2j"
      }
      tim.outputTrackInfo(false, trackId)
      tim.outputTrackInfo(true, trackId)
    }
  }
}
