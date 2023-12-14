package model

enum class Hand {
    ROCK,
    PAPER,
    SCISSORS;

    fun emojiRepresentation(): String {
        return when (this) {
            ROCK -> "🪨"
            PAPER -> "📃"
            SCISSORS -> "✂️"
        }
    }

    fun outcomeFrom(other: Hand): Outcome {
        return Outcome.gameOutcome(this, other)
    }
}