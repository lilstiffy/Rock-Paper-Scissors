package game

import model.Hand
import model.Hand.*
import model.Outcome

/**
 * The AI for the game.
 * Warning: this AI can get angry if it loses too much.
 */
class GameAI {
    private var lastSeenHand: Hand? = null

    // Anger management
    private var isAngry = false
    private var lossSpree = 0

    // Keep track of the opponent's recent moves.
    private val opponentMovesHistory = mutableListOf<Hand>()

    private fun playRandom(): Hand {
        return Hand.values().random()
    }

    /**
     * Make a decision based on the opponent's recent moves.
     */
    private fun makeDecisionBasedOnOpponentHistory(): Hand {
        // Oh, the AI lost its sanity
        if (isAngry)
            //TODO: Spice things up a bit

        if (opponentMovesHistory.size >= 2) {
            val lastMove = opponentMovesHistory.last()
            val secondToLastMove = opponentMovesHistory[opponentMovesHistory.size - 2]

            // If the opponent repeated the same move twice, counter it.
            if (lastMove == secondToLastMove) {
                return when (lastMove) {
                    ROCK -> PAPER
                    PAPER -> SCISSORS
                    SCISSORS -> ROCK
                }
            }
        }

        // Default to the regular strategy if no clear pattern is observed.
        return playRandom()
    }

    fun reactToOutcome(outcome: Outcome, userHand: Hand) {
        lastSeenHand = userHand
        opponentMovesHistory.add(lastSeenHand!!)

        // The AI doesn't like the opponent winning or even drawing a round.
        if (outcome != Outcome.LOSS) {
            lossSpree++
        } else {
            lossSpree = 0
        }
        // Update internal state and become angry if the AI will lose.
        isAngry = lossSpree >= 2
    }

    /**
     * Make a decision based on the opponent's recent moves.
     */
    fun makeDecision(): Hand {
        return makeDecisionBasedOnOpponentHistory()
    }

}
