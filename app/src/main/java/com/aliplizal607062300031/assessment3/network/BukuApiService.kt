package com.aliplizal607062300031.assessment3.network

import com.aliplizal607062300031.assessment3.model.Buku
import com.aliplizal607062300031.assessment3.model.OpStatus
import com.squareup.moshi.Moshi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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
    @Multipart
    @POST("buku.php")
    suspend fun postHewan(
        @Header("Authorization") userId: String,
        @Part("judul") judul: RequestBody,
        @Part("kategori") kategori: RequestBody,
        @Part("status") status: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
}

object BukuApi {
    val service: BukuApiService by lazy {
        retrofit.create(BukuApiService::class.java)
    }

    fun getBukuUrl(fotoUrl: String): String {
        return "$BASE_URL$fotoUrl.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }