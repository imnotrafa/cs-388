package com.codepath.articlesearch

import TvShow
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        bylineTextView = findViewById(R.id.mediaByline)
        abstractTextView = findViewById(R.id.mediaAbstract)
        val voteCountTextView = findViewById<TextView>(R.id.voteCount)


        // TODO: Get the extra from the Intent
        val show = intent.getSerializableExtra(SHOW_EXTRA) as TvShow



        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = show.name
        bylineTextView.text = "First air date: " + show.firstAirDate
        abstractTextView.text = "Rating: " + show.ratingScore.toString()
        voteCountTextView.text = "Vote Count" + show.voteCount.toString()

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+show.imageUrl.toString())
            .into(mediaImageView)
    }
}