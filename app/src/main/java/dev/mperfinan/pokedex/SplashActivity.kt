package dev.mperfinan.pokedex

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mperfinan.pokedex.ui.core.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PokedexTheme {
                SplashScreen(
                    onTimeout =
                        {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        },
                )
            }
        }
    }

    companion object {
        const val INITIAL_BOUNCE_SCALE = 0.5f
        const val INITIAL_ANIMATION_FADE = 0f
        const val SPLASH_SCREEN_DURATION_DISPLAY = 2000L

        // TODO-LATER: this will be removed later once theming is finalized
        const val TEXT_COLOR = 0xFFDF2C2C
    }
}

@Composable
private fun SplashScreen(onTimeout: () -> Unit) {
    // Start smaller for bounce
    val scale = remember { Animatable(SplashActivity.INITIAL_BOUNCE_SCALE) }
    val alpha = remember { Animatable(SplashActivity.INITIAL_ANIMATION_FADE) }

    LaunchedEffect(Unit) {
        // Bounce effect for the Pokemon ball
        scale.animateTo(
            targetValue = 1f,
            animationSpec =
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
        )

        // Fade-in text after a short delay
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 300),
        )

        // Keep splash visible for 2s
        delay(timeMillis = SplashActivity.SPLASH_SCREEN_DURATION_DISPLAY)
        onTimeout()
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash_pokemon_ball),
                contentDescription = "Pokemon Ball",
                modifier =
                    Modifier
                        .size(120.dp)
                        // animated bounce
                        .scale(scale.value),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color(SplashActivity.TEXT_COLOR),
                // animated fade
                modifier = Modifier.alpha(alpha.value),
            )
        }
    }
}

@PhonePreviews
@Composable
fun SplashScreenPreview() {
    PokedexTheme {
        SplashScreen {}
    }
}
