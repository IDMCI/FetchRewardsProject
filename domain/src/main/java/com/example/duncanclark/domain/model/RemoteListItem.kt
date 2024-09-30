package com.example.duncanclark.domain.model

import kotlinx.serialization.SerialName

typealias RemoteListItems = List<RemoteListItem>

data class RemoteListItem(
    @SerialName("id") val id: Long,
    @SerialName("listId") val listId: Int,
    @SerialName("name") val name: String?,
)

fun RemoteListItems.removeUnknownNames(): RemoteListItems {
    return this.filter { !it.name.isNullOrEmpty() }
}