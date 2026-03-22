package app.pandev.mw.get_track_info_green.track_info.persistent_cache

import app.pandev.mw.get_track_info_green.Config
import app.pandev.mw.get_track_info_green.green_process.GreenTrackInfoManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.use

class PersistentTrackInfoCache {
  private val logger: Logger = LoggerFactory.getLogger(GreenTrackInfoManager::class.java)

  private var cacheTableCreateStmt: String =
    "CREATE TABLE IF NOT EXISTS track_info_cache (id TEXT PRIMARY KEY NOT NULL, json_data TEXT NOT NULL)"

  constructor() {
    createCacheFilePath()

    try {
      DriverManager.getConnection(getConnectionUrl()).use { conn ->
        if (conn == null) {
          return
        }
        val stmt = conn.createStatement()
        stmt.executeUpdate(cacheTableCreateStmt)
        stmt.close()
      }
    } catch (e: SQLException) {
      logger.error(e.toString(), e)
    }
  }

  private fun createCacheFilePath() {
    val path = Paths.get(Config.getAppDataPath())
    try {
      Files.createDirectories(path)
    } catch (e: SecurityException) {
      logger.error("Permission denied: ${e.toString()}", e)
    } catch (e: IOException) {
      logger.error("Failed to create directories: ${e.toString()}", e)
    }
  }

  private fun getConnectionUrl(): String {
    return "jdbc:sqlite:" + Config.getAppDataPath() + File.separator + "cache.sqlite3"
  }

  fun add(item: PersistentTrackInfoCacheItem) {
    try {
      DriverManager.getConnection(getConnectionUrl()).use { conn ->
        if (conn == null) {
          return
        }
        val insertTrackInfoCache = "INSERT INTO track_info_cache VALUES(?,?)"
        val stmt = conn.prepareStatement(insertTrackInfoCache)

        stmt.setString(1, item.id)
        stmt.setString(2, item.json_data)

        stmt.executeUpdate()
      }
    } catch (e: SQLException) {
      logger.error(e.toString(), e)
    }
  }

  fun get(id: String): PersistentTrackInfoCacheItem? {
    try {
      DriverManager.getConnection(getConnectionUrl()).use { conn ->
        if (conn == null) {
          return null
        }
        val selectTrackInfoCache = "SELECT * FROM track_info_cache WHERE id = ?"
        val stmt = conn.prepareStatement(selectTrackInfoCache)

        stmt.setString(1, id)

        val rs = stmt.executeQuery()
        if (rs.next()) {
          return PersistentTrackInfoCacheItem(id, rs.getString("json_data"))
        } else {
          return null
        }
      }
    } catch (e: SQLException) {
      logger.error(e.toString(), e)
    }
    return null
  }
}
