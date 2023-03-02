package com.example.basecomposeproject.extension

import android.app.Activity
import android.util.Log
import java.io.IOException
import java.io.InputStream

//
fun Activity.loadJsonFromAsset(fileName: String): String? {
    var json: String? = null
    json = try {
        val inputStream: InputStream = assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        Log.e("loadJsonFromAsset", "${ex.message}")
        return null
    }
    return json
}