package com.williamdemirci.cocktailapi.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "1"

interface CocktailService {

    @GET("api/json/v1/{apiKey}/search.php?")
    fun getCocktailByName(
        @Path("apiKey")
        apiKey: String = API_KEY,
        @Query("s")
        search: String,
    ): Call<Drinks>
}
