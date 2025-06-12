package com.aliplizal607062300031.assessment3.network

import com.aliplizal607062300031.assessment3.model.Buku
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://manajemenbacabuku.rf.gd/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BukuApiService {
    @GET("read.php")
    suspend fun getBuku(): List<Buku>
}

object BukuApi {
    val service: BukuApiService by lazy {
        retrofit.create(BukuApiService::class.java)
    }

    fun getBukuUrl(fotoUrl: String): String {
        return "$BASE_URL$fotoUrl.jpg"
    }
}