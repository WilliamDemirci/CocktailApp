package com.williamdemirci.cocktailapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamdemirci.cocktailapi.data.CocktailData
import com.williamdemirci.cocktailapi.data.CocktailService
import com.williamdemirci.cocktailapi.data.Drinks
import com.williamdemirci.cocktailapi.view.CocktailAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var listOfDrinks = ArrayList<CocktailData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchBar = findViewById<SearchView>(R.id.searchBar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val cocktailAdapter = CocktailAdapter(listOfDrinks)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = cocktailAdapter

        initSearchBar(searchBar, cocktailAdapter)
    }

    private fun initSearchBar(
        searchBar: SearchView,
        cocktailAdapter: CocktailAdapter,
    ) {
        searchBar.queryHint = "Search a cocktail"
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                apiSearch(
                    searchText = newText,
                    adapter = cocktailAdapter,
                )
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                apiSearch(
                    searchText = newText,
                    adapter = cocktailAdapter,
                )
                return false
            }
        })
    }

    fun apiSearch(
        searchText: String,
        adapter: CocktailAdapter,
    ) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val cocktailService: CocktailService = retrofit.create(CocktailService::class.java)
            val call = cocktailService.getCocktailByName(search = searchText)

            call.enqueue(object : Callback<Drinks> {
                override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {
                    response.body()?.drinks?.let { responseListOfDrinks ->
                        listOfDrinks?.clear()
                        for (cocktail in responseListOfDrinks) {
                            listOfDrinks.add(cocktail)
                        }

                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<Drinks>, throwable: Throwable) {
                    Log.e("MainActivityError", throwable.message.toString())
                }
            })
        } catch (e: Exception) {
            Log.e("MainActivityError", e.message.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivityDebug", "Hey je suis dans le onPause()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivityDebug", "Hey je suis dans le onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivityDebug", "Hey je suis dans le onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivityDebug", "Hey je suis dans le onDestroy()")
    }
}