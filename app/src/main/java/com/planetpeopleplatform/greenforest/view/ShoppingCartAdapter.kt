package com.planetpeopleplatform.greenforest.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.planetpeopleplatform.greenforest.R
import com.planetpeopleplatform.greenforest.model.CartItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_list_item.view.*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        // The layout design used for each list item
        val layout = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)

        return ViewHolder(
            layout
        )
    }

    // This returns the size of the list.
    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(cartItems[position])
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(cartItem: CartItem) {

            // This displays the cart item information for each item
            Picasso.get().load(cartItem.item.photos[0]).fit().into(itemView.product_image)

            itemView.product_name.text = cartItem.item.name

            itemView.product_price.text = "$${cartItem.item.price}"

            itemView.product_quantity.text = cartItem.quantity.toString()

        }
    }

}