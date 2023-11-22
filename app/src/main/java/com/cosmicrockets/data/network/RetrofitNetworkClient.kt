package com.cosmicrockets.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.cosmicrockets.data.NetworkClient
import com.cosmicrockets.data.network.dto.Response
import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class RetrofitNetworkClient(private val context: Context) : NetworkClient {
    private val baseUrl = "https://api.spacexdata.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val spaceXService = retrofit.create(SpaceXAPI::class.java)


    override fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }
        try {
            if (dto == "rocket_request") {
                val resp = spaceXService.searchRockets().execute()
                val body = resp.body()
                return body?.apply { resultCode = 200 } ?: Response().apply { resultCode = resp.code() }
            }
        }catch (e: Exception){
            return Response().apply { resultCode = 400 }
        }

        return Response().apply { resultCode = 400 }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}