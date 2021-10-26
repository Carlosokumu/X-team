package com.example.x_team

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkingModule: Module = module {

    single {



        OkHttpClient.Builder()
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
/*
   Aggregated modules
 */
val appModules: List<Module> = listOf(
    networkingModule,
    mainviewmodel,

)

