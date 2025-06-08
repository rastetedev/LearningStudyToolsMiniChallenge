package com.raulastete.studyappminichallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.text.contains

@OptIn(FlowPreview::class)
class SearchableStudyListViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _filteredItems = MutableStateFlow<List<StudyTopic>>(studyTopics)
    val filteredItems = _filteredItems.asStateFlow()

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .map { query ->
                    if (query.isBlank()) {
                        studyTopics
                    } else {
                        studyTopics.filter {
                            it.topic.contains(query, ignoreCase = true) ||
                                    it.categories.any { it.contains(query, ignoreCase = true) }
                        }
                    }
                }
                .collect { filtered ->
                    _filteredItems.value = filtered
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}
