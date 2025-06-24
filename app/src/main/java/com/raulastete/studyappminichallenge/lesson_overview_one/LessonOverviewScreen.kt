/**
 * Pure UI challenge
 * This challenge tests your ability to stretch your Compose skills—because great UIs should flex on both phones and tablets!
 **/

package com.raulastete.studyappminichallenge.lesson_overview_one

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulastete.studyappminichallenge.DeviceMode
import com.raulastete.studyappminichallenge.R
import com.raulastete.studyappminichallenge.ui.theme.BackgroundGreen
import com.raulastete.studyappminichallenge.ui.theme.BackgroundPurple
import com.raulastete.studyappminichallenge.ui.theme.Black
import com.raulastete.studyappminichallenge.ui.theme.BlackVariant
import com.raulastete.studyappminichallenge.ui.theme.FirstGradient
import com.raulastete.studyappminichallenge.ui.theme.Gray
import com.raulastete.studyappminichallenge.ui.theme.MainGreen
import com.raulastete.studyappminichallenge.ui.theme.MainPurple
import com.raulastete.studyappminichallenge.ui.theme.SecondGradient
import com.raulastete.studyappminichallenge.ui.theme.SecondaryBackground
import com.raulastete.studyappminichallenge.ui.theme.ThirdGradient
import com.raulastete.studyappminichallenge.ui.theme.White
import com.raulastete.studyappminichallenge.ui.theme.montserratFamily
import com.raulastete.studyappminichallenge.ui.theme.poltawskinowyFamily

@Composable
fun LessonOverviewScreen(
    deviceMode: DeviceMode
) {
    val paddingValues = when (deviceMode) {
        DeviceMode.PhonePortrait, DeviceMode.PhoneLandscape -> PaddingValues(
            horizontal = 20.dp,
            vertical = 28.dp
        )

        DeviceMode.TabletPortrait, DeviceMode.TabletLandscape -> PaddingValues(
            horizontal = 32.dp,
            vertical = 36.dp
        )
    }

    val headerAlignment = when (deviceMode) {
        DeviceMode.PhonePortrait -> Alignment.Start
        else -> Alignment.CenterHorizontally
    }

    val headerTextAlign = when (deviceMode) {
        DeviceMode.PhonePortrait -> TextAlign.Start
        else -> TextAlign.Center
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainPurple)
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 8.dp)
    ) {

        LessonOverview(
            paddingValues = paddingValues,
            headerAlignment = headerAlignment,
            headerTextAlign = headerTextAlign
        )

    }
}

@Composable
fun LessonOverview(
    paddingValues: PaddingValues,
    headerAlignment: Alignment.Horizontal,
    headerTextAlign: TextAlign
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
    ) {
        Column(horizontalAlignment = headerAlignment) {
            Text(
                text = "Physics Crash Course",
                textAlign = headerTextAlign,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = poltawskinowyFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                lineHeight = 40.sp,
                color = Black
            )

            Spacer(Modifier.height(8.dp))

            Text(
                stringResource(R.string.placeholder_main_body),
                textAlign = headerTextAlign,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = BlackVariant
            )

            Spacer(Modifier.height(16.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LevelChip()
                CategoryChip(
                    text = "Science",
                    colorContainer = BackgroundGreen,
                    textColor = MainGreen
                )
                CategoryChip(
                    text = "Physics",
                    colorContainer = BackgroundGreen,
                    textColor = MainGreen
                )
                TimerChip(text = "15 mins")
            }
        }

        Spacer(Modifier.height(24.dp))

        HorizontalDivider(
            color = Gray
        )

        Spacer(Modifier.height(24.dp))

        Text(
            "What youʼll learn:",
            fontFamily = montserratFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            color = Black
        )

        Spacer(Modifier.height(12.dp))

        TopicItem("Understand Newton's three laws of motion")
        TopicItem("Explain the principle of energy conservation")
        TopicItem("Identify real-world examples of kinetic and potential energy")
        TopicItem("Solve simple physics problems involving force and mass")
        TopicItem("Apply concepts of momentum in everyday scenarios")

        Spacer(Modifier.weight(1f))

        TeacherCard(
            padding = PaddingValues(8.dp),
            imageSize = 40.dp
        )

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun LevelChip() {
    Row(
        Modifier
            .background(color = BackgroundPurple, CircleShape)
            .clip(CircleShape)
            .padding(vertical = 4.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.intermediate_icon),
            contentDescription = null,
            tint = MainPurple
        )
        Text(
            "Intermediate",
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            color = MainPurple
        )
    }
}

@Composable
fun CategoryChip(text: String, colorContainer: Color, textColor: Color) {
    Row(
        Modifier
            .background(color = colorContainer, CircleShape)
            .clip(CircleShape)
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            color = textColor
        )
    }
}

@Composable
fun TimerChip(text: String) {
    Row(
        Modifier
            .background(color = White, CircleShape)
            .border(
                width = 1.dp,
                color = Gray,
                shape = CircleShape
            )
            .clip(CircleShape)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.clock_icon),
            contentDescription = null,
            tint = MainPurple
        )
        Text(
            text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            color = BlackVariant
        )
    }
}

@Composable
fun TopicItem(text: String) {
    Row(
        modifier = Modifier.padding(bottom = 6.dp),
        verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.bullet_point_icon),
            modifier = Modifier.padding(top = 4.dp),
            contentDescription = null,
            tint = MainPurple
        )

        Text(
            text = text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            color = Black,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
fun TeacherCard(padding: PaddingValues, imageSize: Dp) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(
                color = SecondaryBackground,
                shape = RoundedCornerShape(0.5f)
            )
            .clip(RoundedCornerShape(0.5f))
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            FirstGradient,
                            SecondGradient,
                            ThirdGradient,
                            White
                        )
                    )
                )
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.placeholder_teacher),
                modifier = Modifier.clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }

        Spacer(Modifier.width(10.dp))

        Text(
            "Dr. Eleanor Maxwell",
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            color = Black
        )
    }
}

