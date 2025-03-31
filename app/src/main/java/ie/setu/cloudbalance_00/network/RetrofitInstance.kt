package ie.setu.cloudbalance_00.network

import android.util.Log
import ie.setu.cloudbalance_00.viewmodel.AuthTokenProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val authInterceptor = Interceptor { chain ->
        val token = AuthTokenProvider.idToken // or accessToken if needed
        val requestBuilder = chain.request().newBuilder()

        if (token != null) {
            Log.d("AuthInterceptor", "Using token: $token")
            requestBuilder.addHeader("Authorization", "Bearer $token")
        } else {
            Log.w("AuthInterceptor", "No token found")
        }

        chain.proceed(requestBuilder.build())
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://htjfy9rw13.execute-api.us-east-1.amazonaws.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
