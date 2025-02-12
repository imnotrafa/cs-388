package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishItemAdapter(private val items: List<WishItem>) :
    RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.ItemNameView)
        val itemPrice: TextView = itemView.findViewById(R.id.ItemPriceView)
        val itemUrl: TextView = itemView.findViewById(R.id.ItemUrlView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.whishlist_items, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = item.price
        holder.itemUrl.text = item.referenceUrl

        holder.itemUrl.setOnClickListener(){
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.referenceUrl))
                ContextCompat.startActivity(it.context, browserIntent, null)

            } catch (e: ActivityNotFoundException) {
                 Toast.makeText(it.context,"Invalid URL",Toast.LENGTH_SHORT).show()
            }
        }



    }
}
