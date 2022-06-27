package org.d3if2009.pyramidvolume.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2009.pyramidvolume.model.Pyramid
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/NaufalZainR/PBO-10/main/api/"
private const val BASE_URL_IMG = "https://raw.githubusercontent.com/NaufalZainR/PBO-10/main/api/img/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PyramidApiService {
    @GET("piramida.json")
    suspend fun getResult(): List<Pyramid>
}

object PyramidApi {
    val service: PyramidApiService by lazy {
        retrofit.create(PyramidApiService::class.java)
    }
    fun getPyramidUrl(image: String): String {
        return "$BASE_URL_IMG$image"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }