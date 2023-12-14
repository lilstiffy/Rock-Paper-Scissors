package game

import model.Hand
import model.Outcome


/**
 * A round of the game.
 */
data class GameRound(
    val yourHand: Hand,
    val aiHand: Hand
) {
    fun displayText(): String {
        val outcome = Outcome.gameOutcome(yourHand, aiHand)
        return when (outcome) {
            Outcome.WIN -> "Your ${yourHand.emojiRepresentation()} wins against the AI's ${aiHand.emojiRepresentation()}."
            Outcome.DRAW -> "Your ${yourHand.emojiRepresentation()} draws against the AI's ${aiHand.emojiRepresentation()}."
            Outcome.LOSS -> "Your ${yourHand.emojiRepresentation()} loses against the AI's ${aiHand.emojiRepresentation()}."
        }
    }
}
