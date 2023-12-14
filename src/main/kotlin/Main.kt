import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import game.GameAI
import game.GameRound
import model.Hand

@Composable
fun App(viewModel: GameViewModel) {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row {
                // Score text
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.CenterVertically)
                        .padding(all = 8.dp),
                    text = "You: ${viewModel.yourScore}",
                    textAlign = TextAlign.Center
                )

                // Score text
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(all = 8.dp),
                    text = "AI: ${viewModel.aiScore}",
                    textAlign = TextAlign.Center
                )
            }

            // Battle text
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(all = 8.dp),
                text = viewModel.gameRound?.displayText() ?: "Play a hand!",
                style = TextStyle(
                    fontSize = 12.sp
                ),
                textAlign = TextAlign.Center
            )

            // Play hand ROCK
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                onClick = { viewModel.onHandPlayed(Hand.ROCK) }
            ) {
                Text("🪨")
            }

            // Play hand PAPER
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                onClick = { viewModel.onHandPlayed(Hand.PAPER) }
            ) {
                Text("📃")
            }

            // Play hand SCISSORS
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                onClick = { viewModel.onHandPlayed(Hand.SCISSORS) }
            ) {
                Text("✂️")
            }

            // watermark
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(all = 8.dp),
                text = "Made with ❤️ by lilstiffy",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontSize = 10.sp
                ),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

class GameViewModel {
    var yourScore by mutableStateOf(0)
    var aiScore by mutableStateOf(0)
    var gameRound by mutableStateOf<GameRound?>(null)
    private val gameAI = GameAI()

    fun onHandPlayed(hand: Hand) {
        val aiHand = gameAI.makeDecision()
        gameRound = GameRound(hand, aiHand)

        val yourOutcome = hand.outcomeFrom(aiHand)
        yourScore += yourOutcome.pts

        gameAI.reactToOutcome(yourOutcome, hand)

        val aiOutcome = aiHand.outcomeFrom(hand)
        aiScore += aiOutcome.pts
    }
}

fun main() = application {
    val viewModel = GameViewModel()
    Window(
        title = "Rock Paper Scissors",
        state = WindowState(
            width = 250.dp,
            height = 300.dp
        ),
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
        App(viewModel)
    }
}
