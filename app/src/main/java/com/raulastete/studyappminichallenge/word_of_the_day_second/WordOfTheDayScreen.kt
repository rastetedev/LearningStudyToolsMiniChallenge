/**
 * This challenge tests your “wordsmanship” in Compose layouting, styling, and TTS basics—no definition
 * dodging allowed!
 */

package com.raulastete.studyappminichallenge.word_of_the_day_second

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.raulastete.studyappminichallenge.R
import com.raulastete.studyappminichallenge.ui.theme.Black
import com.raulastete.studyappminichallenge.ui.theme.BlackVariant
import com.raulastete.studyappminichallenge.ui.theme.FirstGradient
import com.raulastete.studyappminichallenge.ui.theme.MainPurple
import com.raulastete.studyappminichallenge.ui.theme.SecondGradient
import com.raulastete.studyappminichallenge.ui.theme.ThirdGradient
import com.raulastete.studyappminichallenge.ui.theme.White
import com.raulastete.studyappminichallenge.ui.theme.montserratFamily
import com.raulastete.studyappminichallenge.ui.theme.poltawskinowyFamily
import kotlin.random.Random

@Composable
fun WordOfTheDayScreen() {

    val context = LocalContext.current
    var textToSpeech: TextToSpeech? = null

    DisposableEffect(Unit) {
        textToSpeech =
            TextToSpeech(context) {
                if (it == TextToSpeech.SUCCESS) {
                    Log.d("TextToSpeech", "Initialization Success")
                } else {
                    Log.d("TextToSpeech", "Initialization Error")
                }
            }

        onDispose {
            textToSpeech.shutdown()
        }
    }

    val wordRandomly = remember { wordDefinitionList.random() }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        FirstGradient,
                        SecondGradient,
                        ThirdGradient
                    )
                )
            )
    ) {
        val (wordCard, playButton) = createRefs()

        Card(
            modifier = Modifier.constrainAs(wordCard) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 120.dp)
            ) {
                Text(
                    text = wordRandomly.word,
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = poltawskinowyFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    color = Black
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = wordRandomly.definition,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 19.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    color = BlackVariant

                )
            }
        }

        IconButton(
            onClick = {
                textToSpeech?.let {
                    playWordAndDefinition(it, wordRandomly)
                }
            },
            modifier = Modifier
                .size(80.dp)
                .constrainAs(playButton) {
                    top.linkTo(wordCard.bottom, margin = 40.dp)
                    end.linkTo(wordCard.end)
                    start.linkTo(wordCard.start)
                },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MainPurple,
                contentColor = White
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.volume_icon),
                contentDescription = null
            )
        }
    }
}

fun playWordAndDefinition(
    textToSpeech: TextToSpeech,
    wordDefinition: WordDefinition
) {

    fun playWord() {
        textToSpeech.speak(
            wordDefinition.word,
            TextToSpeech.QUEUE_FLUSH, null,
            Random.nextInt(1000).toString()
        )
        textToSpeech.speak(
            wordDefinition.definition,
            TextToSpeech.QUEUE_ADD, null,
            Random.nextInt(1000).toString()
        )
    }

    if (textToSpeech.isSpeaking) {
        textToSpeech.stop()
        playWord()
    } else {
        playWord()
    }
}