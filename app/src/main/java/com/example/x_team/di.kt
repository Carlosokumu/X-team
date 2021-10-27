package com.example.x_team

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkingModule: Module = module {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = when (BuildConfig.BUILD_TYPE) {
        "release" -> HttpLoggingInterceptor.Level.NONE
        else -> HttpLoggingInterceptor.Level.BODY
    }

    single {



        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }
}



/*
  Stuff related to the dao for interaction with the database
 */
private val mainviewmodel: Module= module {
    single { MainViewModel(get()) }
}
val apiService: Module= module {
    single<ApiService> { get<Retrofit>().create() }
}
/*
   Aggregated modules
 */
val appModules: List<Module> = listOf(
    networkingModule,
    apiService,
    mainviewmodel,

)

