package app.pandev.mw.get_track_info_green

import picocli.CommandLine

@CommandLine.Command(
  name = "mw-get-track-info-green",
  version = ["1.1.0"],
  description = ["Gets the track currently playing on your Spotify Desktop app and outputs it to stdout."],
  mixinStandardHelpOptions = true
)
class CliArgs {
  @CommandLine.Option(
    names = ["--include-extra-data"],
    description = ["Whether to include extra data for all tracks from Spotify's GraphQL endpoint"],
  )
  var includeExtraData = false
}
