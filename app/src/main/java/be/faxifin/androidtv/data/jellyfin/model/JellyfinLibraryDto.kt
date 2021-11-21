package be.faxifin.androidtv.data.jellyfin.model

import org.jellyfin.sdk.model.UUID

data class JellyfinLibraryDto(
    val name: String,
    val id: UUID,
    val collectionType: String? // tvshows/movies/etc..
)