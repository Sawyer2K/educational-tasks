package ru.cft.focusstart.task3.model;

import java.util.Random;

public final class BoardBuilder {

    public static Cell[][] buildBoard(int rows, int columns, int mines) {
        mines = Math.max(0, Math.min(mines, rows * columns));

        var board = new Cell[rows][columns];
        fillingBoardWithCells(board);
        fillingBoardWithMines(board, mines);

        return board;
    }

    private static void fillingBoardWithCells(final Cell[][] board) {
        for (var row = 0; row < board.length; row++) {
            for (var col = 0; col < board[0].length; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    private static void fillingBoardWithMines(final Cell[][] board, int mines) {

        var numberOfMinesSet = 0;

        for (Cell[] cells : board) {
            for (var col = 0; col < board[0].length; col++) {
                if (numberOfMinesSet < mines) {
                    cells[col].insertMine();
                    numberOfMinesSet++;
                }
            }
        }

        shuffleBoard(board);
    }

    private static void shuffleBoard(Cell[][] board) {
        var random = new Random();

        for (var row = board.length - 1; row > 0; row--) {
            for (var col = board[row].length - 1; col > 0; col--) {
                var rowRandom = random.nextInt(row + 1);
                var colRandom = random.nextInt(col + 1);

                var temp = board[row][col];
                board[row][col] = board[rowRandom][colRandom];
                board[rowRandom][colRandom] = temp;
            }
        }
    }
}
