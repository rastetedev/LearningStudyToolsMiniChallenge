package com.raulastete.studyappminichallenge.study_feed_switcher_fourth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulastete.studyappminichallenge.DeviceMode
import com.raulastete.studyappminichallenge.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudyFeedSwitcherScreen(deviceMode: DeviceMode) {

    val items = remember { quickLessons }

    var showHint by remember { mutableStateOf(true) }

    var scrollCounter by rememberSaveable { mutableStateOf(0) }

    val savedPage =
        rememberSaveable { mutableStateOf((Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2) % items.size) }

    val pagerState = rememberPagerState(
        initialPage = savedPage.value,
        pageCount = { Int.MAX_VALUE },
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collectLatest { page ->
                savedPage.value = page
                scrollCounter++
            }
    }

    LaunchedEffect(scrollCounter) {
        showHint = false
        delay(3000)
        showHint = true
    }

    CompositionLocalProvider(LocalOverscrollFactory provides null) {
        when (deviceMode) {
            DeviceMode.TabletPortrait, DeviceMode.PhonePortrait ->
                VerticalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState
                ) { page ->
                    items.getOrNull(
                        page % items.size
                    )?.let { content ->
                        SwitcherVerticalContent(content, showHint)
                    }
                }

            else ->
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState
                ) { page ->
                    items.getOrNull(
                        page % items.size
                    )?.let { content ->
                        SwitcherHorizontalContent(content, showHint)
                    }
                }
        }
    }
}

@Composable
fun SwitcherVerticalContent(
    quickLesson: QuickLesson,
    showHint: Boolean
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(brush = quickLesson.gradient)
    ) {
        Spacer(Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentDescription = null,
            tint = White
        )

        Spacer(Modifier.height(8.dp))

        MainContent(
            quickLesson = quickLesson,
            showHint = showHint,
            isLandscape = false
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentDescription = null,
            tint = White
        )

        Spacer(Modifier.height(32.dp))

    }
}

@Composable
fun SwitcherHorizontalContent(
    quickLesson: QuickLesson,
    showHint: Boolean
) {
    Row(
        Modifier
            .fillMaxSize()
            .background(brush = quickLesson.gradient)
    ) {
        Spacer(Modifier.width(56.dp))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            modifier = Modifier.align(Alignment.CenterVertically),
            contentDescription = null,
            tint = White
        )

        Spacer(Modifier.width(51.dp))

        Column(Modifier.weight(1f)) {
            MainContent(
                quickLesson = quickLesson,
                showHint = showHint,
                isLandscape = true
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            modifier = Modifier.align(Alignment.CenterVertically),
            contentDescription = null,
            tint = White
        )

        Spacer(Modifier.width(56.dp))

    }
}

@Composable
fun ColumnScope.MainContent(quickLesson: QuickLesson, showHint: Boolean, isLandscape: Boolean) {

    if (isLandscape) {
        Spacer(Modifier.height(50.dp))
    }

    AnimatedVisibility(
        visible = showHint,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(
            text = "Swipe to see more",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Medium,
                color = White
            ),
        )
    }

    Spacer(Modifier.weight(1f))

    StudyInformation(quickLesson)

    Spacer(Modifier.height(32.dp))
}

@Composable
fun StudyInformation(quickLesson: QuickLesson) {
    Column(Modifier.padding(horizontal = 32.dp)) {
        SubjectChip(text = quickLesson.subject)

        Spacer(Modifier.height(16.dp))

        Text(
            text = quickLesson.title,
            style = MaterialTheme.typography.displayLarge.copy(color = White)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = quickLesson.description,
            style = MaterialTheme.typography.titleSmall.copy(color = White)
        )
    }
}

@Composable
fun SubjectChip(text: String) {
    Row(
        Modifier
            .background(color = White.copy(alpha = 0.2f), CircleShape)
            .clip(CircleShape)
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.sp,
                lineHeight = 24.sp,
            ),
            color = White
        )
    }
}