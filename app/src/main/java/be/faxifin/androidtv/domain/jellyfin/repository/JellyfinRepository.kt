package be.faxifin.androidtv.domain.jellyfin.repository

import be.faxifin.androidtv.data.jellyfin.model.JellyfinLibraryDto

interface JellyfinRepository {
    suspend fun getLibraries() : List<JellyfinLibraryDto>
}