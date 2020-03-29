package com.planetpeopleplatform.greenforest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.planetpeopleplatform.greenforest.service.APIConfig
import com.planetpeopleplatform.greenforest.R
import com.planetpeopleplatform.greenforest.utils.ShoppingCart
import com.planetpeopleplatform.greenforest.model.Item
import com.planetpeopleplatform.greenforest.service.APIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        apiService = APIConfig.getRetrofitClient(
            this
        ).create(APIService::class.java)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this,
            R.color.colorPrimary
        ))

        swipeRefreshLayout.isRefreshing = true

        // assign a layout manager to the recycler view
        products_recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        cart_size.text = ShoppingCart.getShoppingCartSize(
            this
        ).toString()

        getProducts()

        showCart.setOnClickListener {
            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }

    }


    fun getProducts() {
        apiService.getProducts().enqueue(object : retrofit2.Callback<List<Item>> {
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {

                print(t.message)
                Log.d("Data error", t.message)
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {

                swipeRefreshLayout.isRefreshing = false
                products = response.body()!!

                productAdapter =
                    ProductAdapter(
                        this@MainActivity,
                        products
                    )

                products_recyclerview.adapter = productAdapter
                productAdapter.notifyDataSetChanged()

            }

        })
    }

}