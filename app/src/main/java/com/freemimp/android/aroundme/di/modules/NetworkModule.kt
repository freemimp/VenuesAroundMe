package com.freemimp.android.aroundme.di.modules

import android.content.Context
import android.util.Log
import com.freemimp.android.aroundme.BuildConfig
import com.freemimp.android.aroundme.data.FourSquareApi
import com.freemimp.android.aroundme.di.annotations.AppContext
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, okHttpClient: Lazy<OkHttpClient>): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .callFactory { okHttpClient.get().newCall(it) }
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }

    @Provides
    @Singleton
    fun provideClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(apiAuthenticationInterceptor())
                .cache(cache)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun providesHttpCache(@AppContext context: Context): Cache {

        val cacheFile = File(context.cacheDir, "httpCache")
        return Cache(cacheFile, 10 * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d(Tag, message) }).apply {
            this.level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun apiAuthenticationInterceptor() = injectQueryParams(
            CLIENT_ID to BuildConfig.CLIENT_ID,
            CLIENT_SECRET to BuildConfig.CLIENT_SECRET,
            API_QUERY_V to API_VERSION_DATE
    )

    private fun injectQueryParams(vararg params: Pair<String, String>): Interceptor = Interceptor { chain ->

        val originalRequest = chain.request()
        val urlWithParams = originalRequest.url().newBuilder()
                .apply { params.forEach { addQueryParameter(it.first, it.second) } }
                .build()
        val newRequest = originalRequest.newBuilder().url(urlWithParams).build()

        chain.proceed(newRequest)
    }

    @Provides
    fun provideFourSquareApi(retrofit: Retrofit): FourSquareApi = retrofit.create(FourSquareApi::class.java)

    companion object {
        val Tag: String = NetworkModule::class.java.simpleName
        private const val CONNECTION_TIMEOUT = 15L
        private const val CLIENT_ID = "client_id"
        private const val CLIENT_SECRET = "client_secret"
        private const val API_VERSION_DATE = "20190329"
        private const val API_QUERY_V = "v"


    }
}