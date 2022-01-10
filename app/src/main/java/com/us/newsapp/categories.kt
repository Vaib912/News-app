package com.us.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
    }
    fun inNews(view: View) {
        val intent = Intent(this, Indian_News::class.java)
        Toast.makeText(this,"Wait..!!", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
   fun techNews(view: View) {
       val intent = Intent(this, techNews::class.java)
       Toast.makeText(this, "Wait..!!", Toast.LENGTH_SHORT).show()
       startActivity(intent)
   }
    fun spNews(view: View) {
        val intent = Intent(this, sportsNews::class.java)
        Toast.makeText(this, "Wait..!!", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
    fun businessNews(view: View) {
        val intent = Intent(this, businessNews::class.java)
        Toast.makeText(this, "Wait..!!", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}