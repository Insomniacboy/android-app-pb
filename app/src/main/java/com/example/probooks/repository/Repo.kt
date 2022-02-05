package com.example.probooks.repository

import android.content.Context
import com.example.probooks.models.AccessDetail
import com.example.probooks.models.AccessItem
import com.example.probooks.models.EventDetail
import com.example.probooks.models.EventItem
import org.jsoup.Jsoup
import java.io.IOException

class Repo {

    companion object{
        var instance: Repo? = null
        lateinit var mContext: Context
    }
    fun getInstance(context: Context): Repo {
        mContext = context
        if (instance == null)
            instance = Repo()

        return instance!!
    }
    fun getEventsList(): MutableList<EventItem> {
        var listData : MutableList <EventItem> = mutableListOf<EventItem>()
        try {
            val url = "https://probooks.space/"
            val doc = Jsoup.connect(url).get()
            val events = doc.select("div.col-md-3")
            val eventsSize = events.size
            for (i in 0 until eventsSize){
                val title = events.select("div.feature")
                    .select("h5")
                    .eq(i)
                    .text()
                val author = events.select("div.feature")
                    .select("span")
                    .eq(i)
                    .text()
                val eventUrl = "https://probooks.space" + events.select("div.col-md-3")
                    .select("a")
                    .eq(i)
                    .attr ("href")
                val image = "https://probooks.space/" + events.select("div.feature")
                    .select("img")
                    .eq(i)
                    .attr ("src")
                listData.add(EventItem(i, title, author, image, eventUrl))
            }
        }   catch (e: IOException){
            e.printStackTrace()
        }
        return listData
    }

    fun getAccessEventsList(): MutableList<AccessItem> {
        var listData : MutableList <AccessItem> = mutableListOf<AccessItem>()
        try {
            val url = "https://probooks.space/booked"
            val doc = Jsoup.connect(url).get()
            val events = doc.select("div.col-md-3")
            val eventsSize = events.size
            for (i in 0 until eventsSize){
                val title = events.select("div.feature")
                    .select("h5")
                    .eq(i)
                    .text()
                val author = events.select("div.feature")
                    .select("span")
                    .eq(i)
                    .text()
                val eventUrl = "https://probooks.space" + events.select("div.col-md-3")
                    .select("a")
                    .eq(i)
                    .attr ("href")
                val image = "https://probooks.space/" + events.select("div.feature")
                    .select("img")
                    .eq(i)
                    .attr ("src")
                listData.add(AccessItem(i, title, author, image, eventUrl))
            }
        }   catch (e: IOException){
            e.printStackTrace()
        }
        return listData
    }

    fun getEvent(url: String): EventDetail{
        val item = EventDetail()
        try{
            val builder = StringBuilder()
            for (i in 0 until 10) {
                val document = Jsoup.connect(url).get()
                val detail = document.select("div.col-md-8.col-lg-7")
                    .select("p")
                    .eq(i) //To do: select all by cycle
                    .text()
                if(!detail.isBlank())
                builder.append(detail + "\n\n")
            }
            item.detail = builder.toString()
        } catch (e: IOException){
            e.printStackTrace()
        }
        return item
            }

    fun getAccessEvent(url: String): AccessDetail {
        val item = AccessDetail()
        try{
            val builder = StringBuilder()
            for (i in 0 until 10) {
                val document = Jsoup.connect(url).get()
                val detail = document.select("div.col-md-8.col-lg-7")
                    .select("p")
                    .eq(i) //To do: select all by cycle
                    .text()
                if(!detail.isBlank())
                    builder.append(detail + "\n\n")
            }
            item.detail = builder.toString()
        } catch (e: IOException){
            e.printStackTrace()
        }
        return item
    }
}