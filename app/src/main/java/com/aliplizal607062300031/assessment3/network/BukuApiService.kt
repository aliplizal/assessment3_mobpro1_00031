package com.aliplizal607062300031.assessment3.network

import com.aliplizal607062300031.assessment3.model.OpStatus
import com.aliplizal607062300031.assessment3.model.Buku
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

private const val BASE_URL = "https://buku-api-production-28de.up.railway.app/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ListApiSevice {
    @GET("buku")
    suspend fun getList(
        @Header("Authorization") userId: String
    ): List<Buku>

    @Multipart
    @POST("buku/store")
    suspend fun postBuku(
        @Header("Authorization") userId: String,
        @Part("judul") judul: RequestBody,
        @Part("kategori") kategori: RequestBody,
        @Part("status") status: RequestBody,
        @Part gambar: MultipartBody.Part
    ): OpStatus

    @DELETE("buku/delete/{id}")
    suspend fun deleteBuku(
        @Header("Authorization") userId: String,
        @Path("id") id: String
    ):OpStatus

    @Multipart
    @POST("buku/edit/{id}")
    suspend fun editBuku(
        @Header("Authorization") userId: String,
        @Path("id") id: String,
        @Part("judul") judul: RequestBody,
        @Part("kategori") kategori: RequestBody,
        @Part("status") status: RequestBody,
        @Part gambar: MultipartBody.Part
    ): OpStatus
}


object ListApi {
    val service: ListApiSevice by lazy {
        retrofit.create(ListApiSevice::class.java)
    }
    fun getListUrl(gambar: String): String{
        return "https://buku-api-production-28de.up.railway.app/storage/$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }