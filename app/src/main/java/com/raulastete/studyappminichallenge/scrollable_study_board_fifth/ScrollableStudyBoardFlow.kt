package com.raulastete.studyappminichallenge.scrollable_study_board_fifth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.raulastete.studyappminichallenge.R
import com.raulastete.studyappminichallenge.lesson_overview_one.CategoryChip
import com.raulastete.studyappminichallenge.searchable_study_list_third.categoriesColor
import com.raulastete.studyappminichallenge.ui.theme.Black
import com.raulastete.studyappminichallenge.ui.theme.MainPurple
import com.raulastete.studyappminichallenge.ui.theme.SecondaryPurple
import com.raulastete.studyappminichallenge.ui.theme.StrokeColor
import com.raulastete.studyappminichallenge.ui.theme.TertiaryPurple
import com.raulastete.studyappminichallenge.ui.theme.White
import com.raulastete.studyappminichallenge.ui.theme.montserratFamily
import com.raulastete.studyappminichallenge.ui.theme.poltawskinowyFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    object MainList : Destination

    @Serializable
    data class Detail(val title: String, val category: String) : Destination
}

@Composable
fun ScrollableStudyGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Destination.MainList) {
        composable<Destination.MainList> {
            ScrollableStudyBoardScreen(
                navigateToDetail = { lessonTopic ->
                    navController.navigate(
                        Destination.Detail(
                            lessonTopic.title,
                            lessonTopic.category
                        )
                    )
                }
            )
        }
        composable<Destination.Detail> { backStackEntry ->
            val args = backStackEntry.toRoute<Destination.Detail>()

            CourseDetailsScreen(
                lessonTopic = LessonTopic(args.title, args.category),
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Composable
fun ScrollableStudyBoardScreen(
    viewModel: ScrollableStudyBoardViewModel = viewModel(),
    navigateToDetail: (LessonTopic) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()

    val chipsScrollState = rememberScrollState()

    val showFabButton = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 10 } }

    val showReachEndListAlert = remember {
        derivedStateOf {
            val total = lazyListState.layoutInfo.totalItemsCount
            val limit = (total - 10).coerceAtLeast(0)
            lazyListState.firstVisibleItemIndex >= limit

        }
    }

    var showMaxPinnedItemsAlert by remember { mutableStateOf(false) }

    val isScrollingDown = remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        var lastIndex = 0
        var lastOffset = 0
        snapshotFlow {
            lazyListState.firstVisibleItemIndex to lazyListState.firstVisibleItemScrollOffset
        }
            .distinctUntilChanged()
            .collectLatest { (index, offset) ->
                isScrollingDown.value = when {
                    index > lastIndex -> true
                    index < lastIndex -> false
                    offset > lastOffset -> true
                    offset < lastOffset -> false
                    else -> isScrollingDown.value
                }
                lastIndex = index
                lastOffset = offset
            }
    }

    LaunchedEffect(showReachEndListAlert.value) {
        if (showReachEndListAlert.value && isScrollingDown.value) {
            snackbarHostState.showSnackbar(
                message = "You have reached the end of the list!",
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
        }
    }

    LaunchedEffect(showMaxPinnedItemsAlert) {
        if(showMaxPinnedItemsAlert){
            snackbarHostState.showSnackbar(
                message = "Max pinned items! Just 5 pins",
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
            showMaxPinnedItemsAlert = false
        }
    }

    val progress = produceState(0f, lazyListState) {
        snapshotFlow {
            val total = lazyListState.layoutInfo.totalItemsCount
            val visible = lazyListState.layoutInfo.visibleItemsInfo.size
            val first = lazyListState.firstVisibleItemIndex
            if (total > 0) first.toFloat() / (total - visible).coerceAtLeast(1) else 0f
        }
            .distinctUntilChanged()
            .collect { value = it }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TertiaryPurple),
        floatingActionButton = {
            if (showFabButton.value) {
                FabButton(
                    lazyListState = lazyListState,
                    coroutineScope = coroutineScope
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        shape = RoundedCornerShape(16.dp),
                        containerColor = MainPurple,
                        contentColor = White,
                        dismissActionContentColor = White
                    )
                },
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .padding(top = 24.dp)
            ) {
                FlowRow(
                    maxLines = 1,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .horizontalScroll(chipsScrollState)
                ) {
                    uiState.value.subjects.forEachIndexed { index, subject ->
                        if (index == 0) {
                            Spacer(Modifier.width(20.dp)) // Add space after the last subject
                        }
                        SubjectChipScrollable(
                            subject,
                            onClick = {
                                navigateToStickyHeader(
                                    subject,
                                    uiState.value.unpinnedItems,
                                    lazyListState,
                                    coroutineScope
                                )
                            }
                        )
                        if (index == uiState.value.subjects.lastIndex) {
                            Spacer(Modifier.width(20.dp)) // Add space after the last subject
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp),
                    progress = { progress.value },
                    drawStopIndicator = { },
                )

                Spacer(Modifier.height(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    uiState.value.pinnedItems.forEach { item ->
                        LessonTopicCard(
                            lessonTopic = item,
                            isPinned = true,
                            onClick = { navigateToDetail(item) },
                            onPinClick = {
                                viewModel.togglePinnedItem(
                                    item = item,
                                    isPinned = true
                                )
                            }
                        )
                    }
                }

                LazyColumn(
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp,
                        top = 12.dp
                    )
                ) {
                    uiState.value.unpinnedItems.forEach { (subject, lessons) ->
                        stickyHeader(key = subject) {
                            Surface(color = TertiaryPurple, modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 12.dp),
                                    text = subject.uppercase(),
                                    fontFamily = montserratFamily,
                                    fontSize = 15.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = SecondaryPurple
                                )
                            }
                        }
                        items(lessons) { lesson ->
                            LessonTopicCard(
                                lessonTopic = lesson,
                                isPinned = false,
                                onClick = { navigateToDetail(lesson) },
                                onPinClick = {
                                    if(uiState.value.pinnedItems.size >= 5){
                                        showMaxPinnedItemsAlert = true
                                    }
                                    viewModel.togglePinnedItem(
                                        item = lesson,
                                        isPinned = false
                                    )
                                }
                            )
                        }

                    }

                }
            }
        }
    }
}

fun navigateToStickyHeader(
    subject: String,
    unpinnedItems: Map<String, List<LessonTopic>>,
    lazyListState: LazyListState,
    coroutineScope: CoroutineScope
) {
    val headerIndex = unpinnedItems
        .keys
        .indexOf(subject)
    if (headerIndex != -1) {

        var absoluteIndex = 0
        unpinnedItems.entries.take(headerIndex)
            .forEach { entry ->
                absoluteIndex += 1 // header
                absoluteIndex += entry.value.size // items
            }

        coroutineScope.launch {
            lazyListState.animateScrollToItem(absoluteIndex)
        }
    }
}

@Composable
fun FabButton(
    lazyListState: LazyListState,
    coroutineScope: CoroutineScope
) {
    IconButton(
        modifier = Modifier.size(80.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = TertiaryPurple
        ),
        onClick = {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(0)
            }
        },
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.arrow_up_icon),
            contentDescription = null,
            tint = MainPurple
        )
    }
}

@Composable
fun SubjectChipScrollable(text: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = White,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            text = text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            color = Black
        )
    }
}

@Composable
fun LessonTopicCard(
    lessonTopic: LessonTopic,
    isPinned: Boolean,
    onClick: () -> Unit,
    onPinClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isPinned) MainPurple else Transparent
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = lessonTopic.title,
                modifier = Modifier.weight(0.8f),
                fontFamily = poltawskinowyFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                lineHeight = 28.sp
            )

            IconButton(
                onClick = onPinClick,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isPinned) TertiaryPurple
                    else White
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isPinned) R.drawable.pin_icon
                        else R.drawable.pin_inactive_icon
                    ),
                    contentDescription = null,
                    tint = if (isPinned) MainPurple
                    else StrokeColor
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsScreen(
    lessonTopic: LessonTopic,
    navigateBack: () -> Unit
) {
    val chipColor = categoriesColor.random()

    Scaffold(
        containerColor = White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Course Details",
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        lineHeight = 24.sp,
                        color = White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MainPurple
                )
            )
        },

        ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = lessonTopic.title,
                    fontFamily = poltawskinowyFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    lineHeight = 36.sp
                )
                Spacer(Modifier.height(12.dp))

                CategoryChip(
                    text = lessonTopic.category,
                    colorContainer = chipColor.backgroundColor,
                    textColor = chipColor.mainColor
                )
            }
        }
    }

}