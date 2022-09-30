package com.example.probooks.repository

import android.content.Context
import com.example.probooks.R
import com.google.gson.Gson
import java.net.URL
import java.util.*
// In fact this file is useless, so don't pay attention
class StringAdapter(val context: Context) {

    fun main(args: Array<String>) {
        var response = URL("https://probooks.space/YxAjdSjWv8aOpJX2LlfqF3VE3x4=").readText()
        var gson = Gson()

        val post: Response = gson.fromJson(response, Response::class.java)
        val mystring: String = context.getResources().getString(R.string.app_name)
        print(mystring)
        print(post.whatsapp_number)
    }
}
