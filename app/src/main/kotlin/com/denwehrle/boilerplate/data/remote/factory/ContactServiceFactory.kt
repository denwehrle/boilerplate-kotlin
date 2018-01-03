package com.denwehrle.boilerplate.data.remote.factory

import com.denwehrle.boilerplate.data.remote.endpoints.ContactService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [ContactService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 *
 * @author Dennis Wehrle
 */
object ContactServiceFactory {

    fun makeContactService(isDebug: Boolean): ContactService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor(isDebug))
        return makeContactService(okHttpClient, makeGson())
    }

    private fun makeContactService(okHttpClient: OkHttpClient, gson: Gson): ContactService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(ContactService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}