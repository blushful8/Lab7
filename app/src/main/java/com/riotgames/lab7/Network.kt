package com.riotgames.lab7

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Random

class Network(private val context: Context) {
    private lateinit var name: String
    private lateinit var gender: String
    private lateinit var culture: String

    // in 1..100
    private fun randomGenerator() = Random().nextInt(99) + 1

    fun makeRequest(result: (Triple<String, String, String>) -> Unit) {
        val queue = Volley.newRequestQueue(context)

        val getRandomId = randomGenerator()
        val requestUrl = "https://anapioficeandfire.com/api/characters/$getRandomId"

        val jsonRequest = JsonObjectRequest(requestUrl, { objects ->
            val myObject = JSONObject(objects.toString())

            name = myObject.get("name").toString()
            gender = myObject.get("gender") as String
            culture = myObject.get("culture") as String

            Log.d("Request", "id $getRandomId")

            result.invoke(Triple(name, gender, culture))
        }, { error ->
            error.printStackTrace()
        })
        queue.add(jsonRequest)
    }
}