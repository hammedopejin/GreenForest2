package com.planetpeopleplatform.greenforest.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.planetpeopleplatform.greenforest.R
import com.planetpeopleplatform.greenforest.model.CartItem
import com.planetpeopleplatform.greenforest.model.Item
import com.planetpeopleplatform.greenforest.utils.ShoppingCart
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_row_item.view.*

class ProductAdapter(var context: Context, var items: List<Item> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, null)
        return ViewHolder(
            view
        )

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item
        fun bindProduct(item: Item) {

            itemView.product_name.text = item.name
            itemView.product_price.text = "$${item.price.toString()}"
            Picasso.get().load(item.photos[0]).fit().into(itemView.product_image)

            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

                itemView.addToCart.setOnClickListener { view ->

                    val aItem = CartItem(item)

                    ShoppingCart.addItem(aItem, itemView.context)
                    //notify users
                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${item.name} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(ShoppingCart.getCart(itemView.context))

                }

                itemView.removeItem.setOnClickListener { view ->

                    val aItem = CartItem(item)

                    ShoppingCart.removeItem(aItem, itemView.context)

                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${item.name} removed from your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(ShoppingCart.getCart(itemView.context))
                }


            }).subscribe { cart ->
                var quantity = 0

                cart.forEach { cartItem ->
                    quantity += cartItem.quantity
                }

                (itemView.context as MainActivity).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
            }
        }

    }

}