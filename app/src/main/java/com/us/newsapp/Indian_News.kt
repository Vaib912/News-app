package com.us.newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Indian_News : AppCompatActivity(),spItemClicked {

   private lateinit var mAdapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rView = findViewById<RecyclerView>(R.id.rView)
        rView.layoutManager = LinearLayoutManager(this)
        fetch()
        mAdapter = Adapter(this)
        rView.adapter = mAdapter
    }


    private fun fetch() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://newsapi.org/v2/everything?q=india&apiKey=843f25213cda4092869865a497d31019"
        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
            {
                findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<Data>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = Data(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                   newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },


            {
             Toast.makeText(this,"Nahi Chalra yarrr",Toast.LENGTH_LONG).show()
                findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
            }



        )
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        // Access the RequestQueue through your singleton class.
       MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }

    override fun onItemClicked(item: Data) {
        Toast.makeText(this,"Redirecting to this News",Toast.LENGTH_SHORT).show()

        var builder = CustomTabsIntent.Builder();
            val CustomTabsIntent = builder.build();
        CustomTabsIntent.launchUrl(this, Uri.parse(item.url));


        builder = builder.setToolbarColor(ContextCompat.getColor(this@Indian_News, R.color.colorPrimary))
        builder.addDefaultShareMenuItem()

       builder.setShowTitle(true)

       builder.setExitAnimations(this, android.R.anim.cycle_interpolator, android.R.anim.accelerate_decelerate_interpolator)

    }



}


