package be.faxifin.androidtv.data.jellyfin.model

import org.jellyfin.sdk.model.api.BaseItemDto

class JellyfinMapper {
    companion object {
        fun fromBaseItemDto(baseItemDto: BaseItemDto) : JellyfinLibraryDto {
            return JellyfinLibraryDto(
                name = baseItemDto.name!!,
                id = baseItemDto.id,
                collectionType = baseItemDto.collectionType
            )
        }
    }
}