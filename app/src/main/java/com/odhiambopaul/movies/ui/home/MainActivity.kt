package com.odhiambopaul.movies.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.odhiambopaul.movies.R
import com.odhiambopaul.movies.data.Appdb
import com.odhiambopaul.movies.data.entity.MovieEntity
import com.odhiambopaul.movies.network.Response
import com.odhiambopaul.movies.network.ServiceBuilder
import com.odhiambopaul.movies.utils.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    //create an instance of the composite disposable
    private val compositeDisposable = CompositeDisposable()
    private var appdb: Appdb? = null

    //movies ArrayList holds the list of movies retrieved from the local database before they are passed to the MoviesAdapter
    private val movies: ArrayList<MovieEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating the instance of the app database
        appdb = Appdb.getInstance(this)

        //make the network call to retrieve all the movies
        compositeDisposable.add(
            ServiceBuilder.buildservice().getMovies(api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
        // call the function to retrieve all the movies from the local database
        getAllMovies()
    }

    //display a toast on failure
    private fun onFailure(t: Throwable?) {
        Toast.makeText(this, t?.message!!, Toast.LENGTH_SHORT).show()
    }

    // save the movies to the database when the network call is successful
    private fun onResponse(response: Response?) {
        print(response?.results)
        response?.results!!.forEach {
            insertMovie(it)
        }

    }

    //saves all the movies to the database when the network call is successful
    private fun insertMovie(movie: MovieEntity) {
        compositeDisposable.add(
            appdb!!.moviedao()
                .saveMovie(movie)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    //retrieves all the movies from the database
    private fun getAllMovies() {
        compositeDisposable.add(appdb!!.moviedao()
            .getAllMovies()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                movies.clear()
                movies.addAll(it)
                recyclerview.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MoviesAdapter(movies, this@MainActivity)
                }
            }
        )
    }
}
