package com.odhiambopaul.movies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.odhiambopaul.movies.R
import com.odhiambopaul.movies.network.Response
import com.odhiambopaul.movies.network.ServiceBuilder
import com.odhiambopaul.movies.utils.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_moviedetail.*

class Moviedetail : AppCompatActivity() {

    private val compositeDisposable =CompositeDisposable()

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

        compositeDisposable.add(
            ServiceBuilder.buildservice().getTopMovies(api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }
    private fun onFailure(t: Throwable?) {
        Toast.makeText(this,t?.message,Toast.LENGTH_LONG).show()
    }

    private fun onResponse(response: Response?) {
        top_rated_recycler.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@Moviedetail,LinearLayoutManager.HORIZONTAL,false)
            adapter=TopRatedAdapter(response?.results!!,this@Moviedetail)
        }
    }
}
