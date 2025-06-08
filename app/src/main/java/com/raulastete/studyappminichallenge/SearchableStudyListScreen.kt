/*
* This challenge is like speed dating for study topics—type a few letters,
* and watch them line up to impress you. Just don’t ghost your state management.
*/

package com.raulastete.studyappminichallenge

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.studyappminichallenge.ui.theme.CardShadow
import com.raulastete.studyappminichallenge.ui.theme.FirstGradient
import com.raulastete.studyappminichallenge.ui.theme.SecondGradient
import com.raulastete.studyappminichallenge.ui.theme.TertiaryBackground
import com.raulastete.studyappminichallenge.ui.theme.ThirdGradient
import com.raulastete.studyappminichallenge.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableStudyListScreen(
    viewModel: SearchableStudyListViewModel = viewModel()
) {

    val searchState by viewModel.searchQuery.collectAsStateWithLifecycle()
    val studyTopics by viewModel.filteredItems.collectAsStateWithLifecycle()

    var searchInputIsFocus by remember { mutableStateOf(false) }
    var searchContainerBorder = animateColorAsState(
        targetValue = if (searchInputIsFocus) {
            MaterialTheme.colorScheme.primary
        } else {
            Color.Transparent
        },
        animationSpec = tween(100, 0, LinearEasing),
        label = "Background color"
    )

    var searchContainerBackground = animateColorAsState(
        targetValue = if (searchInputIsFocus) {
            MaterialTheme.colorScheme.surface
        } else {
            TertiaryBackground
        },
        animationSpec = tween(100, 0, LinearEasing),
        label = "Background color"
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        FirstGradient.copy(alpha = 0.15f),
                        SecondGradient.copy(alpha = 0.15f),
                        ThirdGradient.copy(alpha = 0.15f),
                        White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(
                        bottom = 12.dp,
                        start = 20.dp,
                        end = 20.dp,
                        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 8.dp
                    ),
            ) {
                Text(
                    text = "Study Topics",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 22.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                BasicTextField(
                    singleLine = true,
                    value = searchState,
                    onValueChange = {
                        viewModel.onSearchQueryChange(it)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(searchContainerBackground.value, CircleShape)
                        .border(
                            width = 1.dp,
                            color = searchContainerBorder.value,
                            shape = CircleShape
                        )
                        .padding(vertical = 12.dp, horizontal = 14.dp)
                        .fillMaxWidth()
                        .onFocusChanged {
                            searchInputIsFocus = it.isFocused
                        },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            Icon(
                                ImageVector.vectorResource(R.drawable.search_icon),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                            )
                            Box(Modifier.weight(1f)) {
                                if (searchState.isEmpty() && !searchInputIsFocus) {
                                    Text(
                                        text = "Search by topic or subject",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Normal
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                innerTextField()
                            }
                        }
                    }
                )
            }

            LazyColumn(
                Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if(studyTopics.isNotEmpty()){
                    items(studyTopics, key = { it.topic }) { studyTopic ->
                        CourseItem(
                            modifier = Modifier
                                .fillMaxWidth(),
                            studyTopic = studyTopic
                        )
                    }
                } else {
                    item {
                        Text(
                            text = "No results found, try searching again! ",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 15.sp,
                                lineHeight = 24.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun CourseItem(modifier: Modifier = Modifier, studyTopic: StudyTopic) {

    Card(
        modifier = modifier.shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            spotColor = CardShadow,
            ambientColor = CardShadow
        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                studyTopic.categories.forEach {

                    val categoryColor =
                        remember { categoryColorMap[it] ?: categoriesColor.first() }

                    CategoryChip(
                        text = it,
                        colorContainer = categoryColor.backgroundColor,
                        textColor = categoryColor.mainColor,
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = studyTopic.topic,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}