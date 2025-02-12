package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var itemsWishlist: RecyclerView
    private lateinit var wishlistAdapter: WishItemAdapter
    private val items = mutableListOf<WishItem>()
    private var validUrl : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        itemsWishlist = findViewById<RecyclerView>(R.id.wishListRV)

        val addItemBtn = findViewById<Button>(R.id.submitBtn)
        val itemName = findViewById<EditText>(R.id.nameInputView)
        val itemPrice = findViewById<EditText>(R.id.priceInputView)
        val itemUrl = findViewById<EditText>(R.id.urlInputView)


        wishlistAdapter = WishItemAdapter(items)
        itemsWishlist.layoutManager = LinearLayoutManager(this)
        itemsWishlist.adapter = wishlistAdapter;

        addItemBtn.setOnClickListener(){

            val name = itemName.text.toString()
            val price = itemPrice.text.toString()
            val url = itemUrl.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()){

                if(validUrl){
                    val newItem = WishItem(name = name, referenceUrl = url, price = price)
                    //adding the new item to the list
                    items.add(newItem)
                    wishlistAdapter.notifyItemInserted(items.size - 1) // Notify adapter of new item
                    itemName.text.clear()
                    itemPrice.text.clear()
                    itemUrl.text.clear()
                }else{
                    Toast.makeText(it.context, "Invalid URL for " + name, Toast.LENGTH_LONG).show()
                }


            }
            else{
                Toast.makeText(it.context, "You Must Fill All the Values", Toast.LENGTH_SHORT).show()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}