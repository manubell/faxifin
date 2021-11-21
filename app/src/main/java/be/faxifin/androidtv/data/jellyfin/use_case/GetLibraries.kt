package be.faxifin.androidtv.domain.jellyfin.use_case

import android.util.Log
import be.faxifin.androidtv.common.Resource
import be.faxifin.androidtv.data.jellyfin.model.JellyfinLibraryDto
import be.faxifin.androidtv.domain.jellyfin.repository.JellyfinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLibraries @Inject constructor(private val repository: JellyfinRepository) {

    operator fun invoke(): Flow<Resource<List<JellyfinLibraryDto>>> = flow {
        try {
            emit(Resource.Loading())
            Log.i("GetLibraries", "Emitted Loading")
            val libraries = repository.getLibraries()
            emit(Resource.Success(libraries))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred while retrieving libraries",
                )
            )
        }
    }
}