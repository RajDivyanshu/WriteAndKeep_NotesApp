package com.kotlinninja.writeandkeepnotes.news.fragment_news

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.news.adapter_news.NewsItemClicked
import com.kotlinninja.writeandkeepnotes.news.adapter_news.NewsListAdapter
import com.kotlinninja.writeandkeepnotes.news.model_news.News

class NewsMainActivity : AppCompatActivity(), NewsItemClicked {
    var recyclerView: RecyclerView? = null
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var mAdapter: NewsListAdapter
    val newsArray = ArrayList<News>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        // linear list displayed
        layoutManager = LinearLayoutManager(this)


//get the response through volley
        val queue = Volley.newRequestQueue(this)
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=8a2e308e3cdf4a668796a36fdda31a10"
        val getRequest: JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                //  Log.e("answer","$it")
                val newsJsonArray = it.getJSONArray("articles")

                for(i in 0 until  newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("publishedAt"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    // Toast.makeText(this,"Entered",Toast.LENGTH_LONG).show()
                    newsArray.add(news)
                    mAdapter = NewsListAdapter(this, newsArray)
                    recyclerView?.adapter = mAdapter
                    recyclerView?.layoutManager = layoutManager
                }

            },
            Response.ErrorListener { error ->

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
        }
        queue.add(getRequest)

        // MySingleton.getInstance(this).addToRequestQueue(getRequest)
    }
//
//    fun fetchData() {
//    val queue = Volley.newRequestQueue(this)
//    val url =
//        "https://newsapi.org/v2/top-headlines?country=us&apiKey=8a2e308e3cdf4a668796a36fdda31a10"
//    val jsonObjectRequest = object : JsonObjectRequest(
//        Request.Method.GET, url, null, Response.Listener {
//            val newsJsonArray = it.getJSONArray("articles")
//            val newsArray = ArrayList<News>()
//            for (i in 0 until newsJsonArray.length()) {
//                val newsJsonObject = newsJsonArray.getJSONObject(i)
//                val news = News(
//                    newsJsonObject.getString("author"),
//                    newsJsonObject.getString("title"),
//                    newsJsonObject.getString("url"),
//                    newsJsonObject.getString("urlToImage")
//                )
//                newsArray.add(news)
//            }
//            mAdapter.updateNews(newsArray)
//
//        },
//        Response.ErrorListener {
//
//        }
//    )
//    {
//    @Throws(AuthFailureError::class)
//    override fun getHeaders(): Map<String, String> {
//        val params: MutableMap<String, String> = HashMap()
//        params["User-Agent"] = "Mozilla/5.0"
//        return params
//    }
//}
//        queue.add(jsonObjectRequest)
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//
//    }


    //leads to the web page when user clicked on it
    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }



    //action bar menus




}