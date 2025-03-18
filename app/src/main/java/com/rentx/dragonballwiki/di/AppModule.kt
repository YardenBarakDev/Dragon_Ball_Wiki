package com.rentx.dragonballwiki.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.rentx.dragonballwiki.MainActivity
import com.rentx.dragonballwiki.data.local.RoomDBConstructor
import com.rentx.dragonballwiki.data.network.KtorRemoteDragonBallSource
import com.rentx.dragonballwiki.data.network.RemoteDataSource
import com.rentx.dragonballwiki.data.repository.DragonBallRepositoryImpl
import com.rentx.dragonballwiki.data.paging.DragonBallPaging
import com.rentx.dragonballwiki.model.DragonBallRepository
import com.rentx.dragonballwiki.presentation.home_page.HomePageVM
import com.rentx.dragonballwiki.presentation.home_page.components.SelectedCharacterVM
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<RoomDBConstructor> {
        Room.databaseBuilder(
            androidContext(),
            RoomDBConstructor::class.java,
            "dragon_ball_db"
        )
            .build()
    }

    single<HttpClient> {
        HttpClient(OkHttp) {

            install(Logging) {
                logger = Logger.SIMPLE
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            engine {
                config {
                    followRedirects(true)
                }
            }
        }
    }
    factory {
        DragonBallPaging(get())
    }
    viewModel {
        HomePageVM(get(), get())
    }
    viewModel {
        SelectedCharacterVM()
    }
    factory {
        DragonBallPaging(get())
    }
    factory<RemoteDataSource> {
        KtorRemoteDragonBallSource(get())
    }
    factory<DragonBallRepository> {
        DragonBallRepositoryImpl(get())
    }
}
