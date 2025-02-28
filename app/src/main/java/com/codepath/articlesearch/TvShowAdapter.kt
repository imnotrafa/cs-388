package com.codepath.articlesearch

import TvShow
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val SHOW_EXTRA = "SHOW_EXTRA"
private const val TAG = "ShowAdapter"

class TvShowAdapter(private val context: Context , private val shows: List<TvShow>) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = shows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount() = shows.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.mediaAbstract)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(show: TvShow) {

            if(show.name.toString().isNotEmpty()){
                titleTextView.text = show.name;
            }
            else{
                titleTextView.text = "No Description"
            }

            abstractTextView.text = show.showDescription
            Log.e("Show",show.toString())
            Glide.with(context)
                .load( "https://image.tmdb.org/t/p/w500"+show.imageUrl)
                .into(mediaImageView)
        }


        override fun onClick(v: View?) {
            val show = shows[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(SHOW_EXTRA,show)
            context.startActivity(intent)
        }
    }
}

