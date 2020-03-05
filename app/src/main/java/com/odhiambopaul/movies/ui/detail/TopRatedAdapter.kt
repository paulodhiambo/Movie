package com.odhiambopaul.movies.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odhiambopaul.movies.R
import com.odhiambopaul.movies.data.entity.MovieEntity
import com.odhiambopaul.movies.utils.image_path

class TopRatedAdapter(val movies: List<MovieEntity>, private val context: Context) :
    RecyclerView.Adapter<TopRatedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.top_rated_item, parent, false)
        return TopRatedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        return holder.bind(movies[position])
    }

}

class TopRatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.top_rated_poster)

    //
    @SuppressLint("SetTextI18n")
    fun bind(movie: MovieEntity) {
        Glide.with(itemView.context).load(image_path + movie.poster_path)
            .into(photo)
    }

}