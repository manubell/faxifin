package be.faxifin.androidtv.di

import android.content.Context
import be.faxifin.androidtv.BuildConfig
import be.faxifin.androidtv.common.Constants
import be.faxifin.androidtv.data.jellyfin.repository.JellyfinRepositoryImpl
import be.faxifin.androidtv.domain.jellyfin.repository.JellyfinRepository
import be.faxifin.androidtv.domain.jellyfin.use_case.GetLibraries
import be.faxifin.androidtv.domain.jellyfin.use_case.JellyfinUsecases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.extensions.userApi
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    suspend fun provideApiClient(@ApplicationContext appContext: Context): ApiClient {
        val jellyfin = createJellyfin {
            context = appContext
            clientInfo = ClientInfo("Android TV", BuildConfig.VERSION_NAME)
        }

        val api = jellyfin.createApi(
            baseUrl = Constants.BASE_URL,
            accessToken = Constants.ACCESS_TOKEN
        )

        api.userId = api.userApi.getCurrentUser().content.id

        return api
    }

    @Provides
    @Singleton
    fun provideJellyfinRepository(): JellyfinRepository {
//        return JellyfinRepositoryImpl(api)
        return JellyfinRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideJellyfinUsecases(repository: JellyfinRepository): JellyfinUsecases {
        return JellyfinUsecases(
            getLibraries = GetLibraries(repository)
        )
    }
}