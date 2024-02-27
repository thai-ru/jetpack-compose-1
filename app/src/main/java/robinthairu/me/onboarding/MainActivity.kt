package robinthairu.me.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import robinthairu.me.onboarding.ui.theme.OnboardingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }
    Surface (modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
    ) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Onboarding App")
        Button(
            modifier = Modifier.padding(vertical = 12.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    remember is used to guard against recomposition
    val IsExpanded = remember {
        mutableStateOf(false)
    }

    val extraPadding = if (IsExpanded.value) 32.dp else 0.dp

    Surface (
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        color = MaterialTheme.colorScheme.error
    ) {
        Row (modifier = Modifier.padding(6.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { IsExpanded.value = !IsExpanded.value },
            ) {
                Text(if (IsExpanded.value) "Show less" else "Show more..")
            }
        }
    }

}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OnboardingTheme {
        Greeting("Android")
    }
}