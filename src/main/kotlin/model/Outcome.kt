package model

import model.Hand.*

enum class Outcome(val pts: Int) {
    WIN(3),
    DRAW(1),
    LOSS(0);

    companion object {
        fun gameOutcome(your: Hand, other: Hand): Outcome {
            return when (your) {
                ROCK -> when (other) {
                    ROCK -> DRAW
                    PAPER -> LOSS
                    SCISSORS -> WIN
                }
                PAPER -> when (other) {
                    ROCK -> WIN
                    PAPER -> DRAW
                    SCISSORS -> LOSS
                }
                SCISSORS -> when (other) {
                    ROCK -> LOSS
                    PAPER -> WIN
                    SCISSORS -> DRAW
                }
            }
        }
    }
}