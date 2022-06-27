package minesweeper.model.board

import minesweeper.model.cell.CellGenerator
import minesweeper.model.cell.Cells
import minesweeper.model.cell.RandomMineLocator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class Board(private val initialBoardState: BoardState) : Area by initialBoardState.area {

    val area: Area
        get() = this.state.area

    val cells: Cells
        get() = this.state.cells

    var state: BoardState = initialBoardState
        private set

    val isFinished: Boolean
        get() = this.state is BoardState.Finished

    fun openCell(coordinate: Coordinate) {
        require(coordinate in this.area)
        this.state = this.state.openCell(coordinate)
    }

    companion object {
        private const val COUNT_OF_FORCE_SAFE_CELL = 1
        val Area.maxMineCountInRandomBoard: Int
            get() = this.cellCount - COUNT_OF_FORCE_SAFE_CELL

        operator fun invoke(area: Area, mineCount: Int) = Board(
            initialBoardState = BoardState.Ready(
                cellGenerator = CellGenerator(area, RandomMineLocator(area, mineCount))
            )
        )
    }
}
