package be.faxifin.androidtv.data.jellyfin.repository

import be.faxifin.androidtv.data.jellyfin.model.JellyfinLibraryDto
import be.faxifin.androidtv.domain.jellyfin.repository.JellyfinRepository
import java.util.*
import javax.inject.Inject

class JellyfinRepositoryImpl @Inject constructor
//    (private val jellyfinApi: ApiClient) : JellyfinRepository {
    () : JellyfinRepository {

//    override suspend fun getLibraries(): List<JellyfinLibraryDto> {
//        val response = jellyfinApi.itemsApi.getItems(
//            userId = jellyfinApi.userId
//        )
//
//        val items = response.content.items?.map { item -> JellyfinMapper.fromBaseItemDto(item) }
//        return items ?: emptyList()
//    }

    override suspend fun getLibraries(): List<JellyfinLibraryDto> {
        val jellyfinDto1 = JellyfinLibraryDto(
            name = "name1",
            collectionType = "collectionType1",
            id = UUID.randomUUID()
        )
        val jellyfinDto2 = JellyfinLibraryDto(
            name = "name2",
            collectionType = "collectionType2",
            id = UUID.randomUUID()
        )

        return listOf(jellyfinDto1, jellyfinDto2)
    }
}