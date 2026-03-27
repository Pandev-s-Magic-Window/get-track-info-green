package app.pandev.mw.get_track_info_green

import picocli.CommandLine

class Main {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val cliArgs = CliArgs()

      CommandLine(cliArgs)
        .parseArgs(*args)

      App(
        includeExtraData = cliArgs.includeExtraData
      )
    }
  }
}
