package ru.cft.focusstart.task3.model;

public final class BoardBuilder {

    public static Cell[][] buildBoard(int rows, int columns) {
        var board = new Cell[rows][columns];
        fillingBoardWithCells(board);

        return board;
    }

    private static void fillingBoardWithCells(final Cell[][] board) {
        for (var row = 0; row < board.length; row++) {
            for (var col = 0; col < board[0].length; col++) {
                board[row][col] = new Cell();
            }
        }
    }
}
