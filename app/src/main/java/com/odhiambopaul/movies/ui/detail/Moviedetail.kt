package com.odhiambopaul.movies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.odhiambopaul.movies.R

class Moviedetail : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedetail)
        val poster_text:String?=intent.getStringExtra("poster")
        val title_text:String?=intent.getStringExtra("title")
        val overview_text:String?=intent.getStringExtra("overview")
        val release_text:String?=intent.getStringExtra("release")
        val ratingtext:String?=intent.getStringExtra("rating")

        val photo: ImageView = findViewById(R.id.moviephoto)
        val title: TextView = findViewById(R.id.movietitle)
        val overview: TextView = findViewById(R.id.movieoverview)
        val rating: TextView = findViewById(R.id.movierating)
        val release:TextView = findViewById(R.id.movierelease)


        Glide.with(this).load(poster_text).into(photo)
        title.text= "Title: $title_text"
        overview.text= "Overview: $overview_text"
        rating.text= "Rating: $ratingtext"
        release.text="Release date: $release_text"
        if (ratingtext.isNullOrEmpty())
        {
            rating.text= "Rating: 1"
        }
        else
        {

            rating.text= "Rating: $ratingtext"
        }

    }
}
