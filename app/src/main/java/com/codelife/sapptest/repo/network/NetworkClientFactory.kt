package com.codelife.sapptest.repo.network

import com.codelife.sapptest.BuildConfig
import com.codelife.sapptest.repo.EndPoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkClientFactory {

    companion object {

        fun create(): EndPoints {

            val interceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else
                interceptor.level = HttpLoggingInterceptor.Level.NONE


            val httpClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.HOST_URL)
                .build()

            return retrofit.create(EndPoints::class.java)
        }
    }
}

