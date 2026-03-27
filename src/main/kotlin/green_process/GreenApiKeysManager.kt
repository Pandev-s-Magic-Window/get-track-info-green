package app.pandev.mw.get_track_info_green.green_process

import app.pandev.mw.get_track_info_green.Config
import com.google.gson.Gson
import com.google.gson.JsonElement
import de.labystudio.spotifyapi.open.totp.model.Secret
import de.labystudio.spotifyapi.open.totp.provider.DefaultSecretProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.tomlj.Toml
import org.tomlj.TomlParseError
import java.io.File
import java.io.IOException
import java.net.URI
import java.nio.file.Paths
import java.util.function.Consumer

class GreenApiKeysManager {
  companion object {

    private val logger: Logger = LoggerFactory.getLogger(GreenApiKeysManager::class.java)

    private val defaultApiKeysFileName = "api_keys.default.toml"
    private val defaultApiKeysFilePath = Config.getAppDataPath() + File.separator + defaultApiKeysFileName
    private val customApiKeysFilePath = Config.getAppDataPath() + File.separator + "api_keys.custom.toml"

    private val backupUrlForApiKeys =
      "https://raw.githubusercontent.com/CycloneAddons/spotify-token-generator/refs/heads/main/secrets/secrets.json"

    fun getKeys(): DefaultSecretProvider? {
      unpackDefaultApiKeysFile()

      try {
        val fromBackupUrl = getFromBackupUrl()
        val fromAppData = getFromAppData()

        if (fromBackupUrl != null && fromAppData != null && fromBackupUrl.secret.version > fromAppData.secret.version) {
          return fromBackupUrl
        }

        if (fromAppData != null) {
          return fromAppData
        }

        return fromBackupUrl

      } catch (e: IOException) {
        logger.error(e.toString(), e)
      }
      return null
    }

    private fun unpackDefaultApiKeysFile() {
      try {
        val inputStream = this::class.java.getResourceAsStream("/$defaultApiKeysFileName")
        val targetFile = File(defaultApiKeysFilePath)
        inputStream.use { input ->
          targetFile.outputStream().use { output ->
            input?.copyTo(output)
          }
        }
      } catch (e: IOException) {
        logger.error(e.toString(), e)
      }
    }

    private fun getFromAppData(): DefaultSecretProvider? {
      try {
        var apiKeysFileSource = Paths.get(defaultApiKeysFilePath)

        val customApiKeysFile = File(customApiKeysFilePath)
        if (customApiKeysFile.exists()) {
          apiKeysFileSource = Paths.get(customApiKeysFilePath)
        }
        val result = Toml.parse(apiKeysFileSource)
        result.errors().forEach(Consumer { error: TomlParseError? -> logger.error(error.toString()) })
        val secretValue = result.getString("totp_value")
        val secretVersion = result.getString("totp_version")

        if (secretValue == null || secretVersion == null) {
          return null
        }
        return DefaultSecretProvider(Secret.fromString(secretValue, secretVersion.toInt()))
      } catch (e: Exception) {
        logger.error(e.toString(), e)
      }
      return null
    }

    private fun getFromBackupUrl(): DefaultSecretProvider? {
      try {
        val rawJsonResponseStr = URI
          .create(backupUrlForApiKeys)
          .toURL()
          .readText()

        val rawJsonResponse = Gson().fromJson(rawJsonResponseStr, JsonElement::class.java) ?: return null

        val rawKeysList = rawJsonResponse.asJsonArray
        if (rawKeysList == null || rawKeysList.size() == 0) {
          return null
        }

        val apiKeysList = rawKeysList.mapNotNull { it.asJsonObject }
        val sortedApiKeysList = apiKeysList.sortedByDescending { it.get("version")?.asString }
        val mostRecentApiKeys = sortedApiKeysList.firstOrNull() ?: return null

        val secret = mostRecentApiKeys.get("secret")?.asString ?: return null
        val version = mostRecentApiKeys.get("version")?.asString ?: return null

        return DefaultSecretProvider(Secret.fromString(secret, version.toInt()))
      } catch (e: Exception) {
        logger.error(e.toString())
      }
      return null
    }

  }
}
