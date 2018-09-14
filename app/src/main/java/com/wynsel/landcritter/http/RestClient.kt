package com.wynsel.landcritter.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {
    companion object {
        private var restClient : RestClient? = null
        private var retrofit : Retrofit? = null
        var callService : CallService? = null

        fun init() {
            if (restClient == null) {
                restClient = RestClient()
                retrofit = retrofitBuilder()
                callService = retrofit?.create(CallService::class.java)
            }
        }

        private fun setupLogger() : HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        private fun httpBuilder() : OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor {
                        val original = it.request()
                        val requestBuilder = original.newBuilder()
                                .header("Content-Type", "application/json")
                        val request = requestBuilder.build()
                        return@addInterceptor it.proceed(request)
                    }
                    .addInterceptor(setupLogger())
                    .build()
            return httpClient.build()
        }

        private fun retrofitBuilder() : Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpBuilder())
                    .build()
        }

    }

}