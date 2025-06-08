package com.raulastete.studyappminichallenge.scrollable_study_board_fifth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.mapValues

class ScrollableStudyBoardViewModel() : ViewModel() {

    private var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    fun togglePinnedItem(item: LessonTopic, isPinned: Boolean) {
        if (isPinned) {
            removePinnedItem(item)
        } else {
            addPinnedItem(item)
        }
    }

    private fun addPinnedItem(item: LessonTopic) {

        if(uiState.value.pinnedItems.size >= 5) {
           return
        }

        _uiState.update {
            val unpinnedItemsMutable = it.unpinnedItems.toMutableMap()
            val pinnedItemsMutable = it.pinnedItems.toMutableList()

            // Remove item from unpinned items if it exists
            unpinnedItemsMutable[item.category]?.let { topics ->
                unpinnedItemsMutable[item.category] = topics.filter { it != item }
            }

            // Add item to pinned items
            pinnedItemsMutable.add(item)

            it.copy(
                unpinnedItems = unpinnedItemsMutable,
                pinnedItems = pinnedItemsMutable
            )
        }
    }

    private fun removePinnedItem(item: LessonTopic) {
        _uiState.update {
            val unpinnedItemsMutable = it.unpinnedItems.toMutableMap()
            val pinnedItemsMutable = it.pinnedItems.toMutableList()

            // Remove item from pinned items if it exists
            pinnedItemsMutable.remove(item)

            // Add item back to unpinned items
            val list = unpinnedItemsMutable[item.category]?.toMutableList() ?: mutableListOf()
            list.add(item)
            unpinnedItemsMutable[item.category] = list

            val sortedMap = unpinnedItemsMutable.mapValues { entry ->
                entry.value.sortedBy { it.title }
            }.toSortedMap()

            it.copy(
                unpinnedItems = sortedMap,
                pinnedItems = pinnedItemsMutable
            )
        }
    }

    init {
        _uiState.update {
            it.copy(
                unpinnedItems = lessonMap,
                subjects = lessonMap.keys.toList()
            )
        }
    }
}

data class UiState(
    val unpinnedItems: Map<String, List<LessonTopic>> = emptyMap(),
    val pinnedItems: List<LessonTopic> = emptyList(),
    val subjects : List<String> = emptyList()
)