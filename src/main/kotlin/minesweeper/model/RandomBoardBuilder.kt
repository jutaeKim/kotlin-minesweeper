package minesweeper.model

import minesweeper.model.board.Board
import minesweeper.model.board.coordinate.BoardArea

class RandomBoardBuilder(private val boardArea: BoardArea, private val mineCount: Int) : BoardBuilder {
    init {
        require(mineCount in 0..boardArea.cellCount)
    }

    override fun createNewBoard(): Board {
        val cellCount = boardArea.columnCount * boardArea.rowCount
        require(mineCount in 1..cellCount)
        val minePositions = boardArea.shuffled().take(mineCount)
        return Board.build(boardArea) { position -> position in minePositions }
    }
}
