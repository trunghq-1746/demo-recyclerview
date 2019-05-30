package com.trunghoang.generalapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trunghoang.generalapp.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesRecyclerAdapter(
    private val dataSet: List<Movie>
) : RecyclerView.Adapter<MoviesRecyclerAdapter.MovieViewHolder>() {
    private var countViewHolder = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        countViewHolder ++
        Log.d("RecyclerView", "New $countViewHolder")
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("RecyclerView", "Use $holder")
        holder.bind(dataSet[position])
    }

    fun setData(newDataSet: List<Movie>) {
        (dataSet as ArrayList).clear()
        dataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    fun addItem(newItem: Movie) {
        (dataSet as ArrayList).add(0, newItem)
        notifyItemInserted(0)
    }

    fun removeItem() {
        (dataSet as ArrayList).removeAt(1)
        notifyItemRemoved(1)
    }

    fun changeItem() {
        dataSet[1].title = "Haha"
        notifyItemChanged(10)
    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textTitle = itemView.textTitle
        private val imageView = itemView.imageView

        fun bind(movie: Movie) {
            textTitle.text = movie.title
            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}