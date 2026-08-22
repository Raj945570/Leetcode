class Solution {

    boolean findEmptyCell(char[][] board, int[] cell) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    cell[0] = i;
                    cell[1] = j;
                    return true;
                }
            }
        }
        return false;
    }

    boolean isSafeToPlace(char[][] board, char ch, int row, int col) {

        // row check
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) return false;
        }

        // column check
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == ch) return false;
        }

        // 3x3 box check
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == ch) return false;
            }
        }

        return true;
    }

    boolean solveSudokuHelper(char[][] board) {

        int[] cell = new int[2];

        // base case
        if (!findEmptyCell(board, cell)) {
            return true; // solved
        }

        int row = cell[0];
        int col = cell[1];

        for (int val = 1; val <= 9; val++) {

            char ch = (char)(val + '0');

            if (isSafeToPlace(board, ch, row, col)) {

                board[row][col] = ch;

                if (solveSudokuHelper(board)) return true;

                board[row][col] = '.'; // backtrack
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }
}