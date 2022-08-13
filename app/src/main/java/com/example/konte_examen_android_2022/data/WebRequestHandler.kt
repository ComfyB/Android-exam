package com.example.konte_examen_android_2022.data


import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import kotlin.concurrent.thread

class WebRequestHandler {
    private var priorityType: String? = null


    fun askQuestion(ToDoString: String): Int {
        val url = "https://8ball.delegator.com/magic/JSON/$ToDoString"
        val client = OkHttpClient()
        thread {
            val request = Request.Builder().url(url).get().build()
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
            val jsonResponse = JSONObject(JSONObject(responseBody.toString()).getString("magic"))

            Log.i("JSONrespo", jsonResponse.toString())

             priorityType = jsonResponse.getString("type")
            Log.i("responsbody", responseBody.toString())
        }.join()
        return when (priorityType?.lowercase()) {
            "affirmative" -> 1
            "neutral" -> 2
            else -> 3

        }
    }
}
/*    var response: Deferred<HttpResponse>
        var responseInString = String()
        runBlocking {
            response =
                async { client.get("https://8ball.delegator.com/magic/JSON/$ToDoString") }
            runBlocking {
                responseInString =
                    response.await().body<JSONArray>().getJSONObject(0)?.get("type").toString()
                        .lowercase()
            }
            Log.i("HTTPResponse", responseInString)
        }
        return when (responseInString) {
            "affirmative" -> 1
            "neutral" -> 2
            else -> 3

        }*/