package com.planetpeopleplatform.greenforest.utils

import android.content.Context
import com.planetpeopleplatform.greenforest.model.CartItem
import io.paperdb.Paper
import io.paperdb.Paper.init

class ShoppingCart {

    companion object {
        fun addItem(cartItem: CartItem, context: Context) {

            val cart =
                getCart(
                    context
                )

            val targetItem = cart.singleOrNull { it.item.id == cartItem.item.id }
            if (targetItem == null) {
                cartItem.quantity++
                cart.add(cartItem)
            } else {
                targetItem.quantity++
            }
            saveCart(cart)
        }

        fun removeItem(cartItem: CartItem, context: Context) {

            val cart =
                getCart(
                    context
                )

            val targetItem = cart.singleOrNull { it.item.id == cartItem.item.id }
            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    targetItem.quantity--
                } else {
                    cart.remove(targetItem)
                }
            }

            saveCart(
                cart
            )
        }

        fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }

        fun getCart(context: Context): MutableList<CartItem> {
            init(context)
            return Paper.book().read("cart", mutableListOf())
        }

        fun getShoppingCartSize(context: Context): Int {
            var cartSize = 0
            getCart(
                context
            ).forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }
    }

}