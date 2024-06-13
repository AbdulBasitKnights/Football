package com.football.league.data.datasource.remote.network

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NetworkHelper {

    fun makeGetRequest(urlString: String): String? {
        var connection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        var jsonResponse: String? = null

        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val inputStream = connection.inputStream
            reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
            jsonResponse = stringBuilder.toString()
        } catch (e: Exception) {
            Log.e("NetworkHelper", "Error making GET request", e)
        } finally {
            connection?.disconnect()
            try {
                reader?.close()
            } catch (e: Exception) {
                Log.e("NetworkHelper", "Error closing reader", e)
            }
        }
        return jsonResponse
    }
}
