package com.example.konte_examen_android_2022.data


import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import kotlin.concurrent.thread

class WebRequestHandler {
    private var priorityType: String? = null //priority type for the request


    fun askQuestion(ToDoString: String): Int { //returns the priority of the question
        val url = "https://8ball.delegator.com/magic/JSON/$ToDoString"
        val client = OkHttpClient()
        thread { //thread to avoid blocking the UI
            val request = Request.Builder().url(url).get().build() //builds the request
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
            try { //try to parse the response
                val jsonResponse =
                    JSONObject(JSONObject(responseBody.toString()).getString("magic"))
                priorityType = jsonResponse.getString("type")

            } catch (e: JSONException) {
                e.printStackTrace();
            }
        }.join()
        return when (priorityType?.lowercase()) {
            "affirmative" -> 1
            "neutral" -> 2
            else -> 3
        }
    }
}
