import java.util.ArrayList;
import java.util.List;

public class EightQueen {
    public static void main(String[] args) {
        char[][] unsolvedBoard = unsolvedBoard();
        char[][] finished = solveBoard(unsolvedBoard, 0);
        if(finished != null) {
            printBoard(finished);
        }
    }

    public static char[][] unsolvedBoard() {
        char[][] newBoard = new char[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                newBoard[j][i] = 'e';
            }
        }

        return newBoard;
    }

    public static char[][] solveBoard(char[][] board, int x){
        if (x == 8) {
            return board;
        } else {
            List<char[][]> validBoards = addQueen(board);
            x++;
            for (char[][] someBoard : validBoards) {
                printBoard(someBoard);
                char[][] returnBoard = solveBoard(someBoard, x);
                if (returnBoard != null) {
                    return returnBoard;
                }
            }
        }
        return null;
    }

    public static List<char[][]> addQueen(char[][] board){
        List<char[][]> boards = new ArrayList<>();
        char[][] newBoard = board.clone();
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[i].length; j++) {
                if (newBoard[i][j] != 'i' && newBoard[i][j]!= 'Q') {
                    boards.add(fillInvalid(newBoard, i, j));

                }
            }
        }
        return boards;
    }

    public static char[][] fillInvalid(char[][] board, int x, int y){
        char[][] newBoard = board.clone();

        newBoard[x][y] = 'Q';
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (newBoard[x][j] != 'Q') {
                    newBoard[x][j] = 'i';
                } else if (newBoard[i][y] != 'Q') {
                    newBoard[i][y] = 'i';
                }
            }
        }
        int startupx = x;
        int startupy = y;
        while(startupx != 0 && startupy != 0){
            startupx--;
            startupy--;
        }

        int startdownx = x;
        int startdowny = y;
        while(startdownx != 0 && startdowny != 7) {
            startdownx--;
            startdowny++;
        }

        while(startupx != 7 && startupy != 7) {
            if(newBoard[startupx][startupy] != 'Q') {
                newBoard[startupx][startupy] = 'i';
            }
            startupx++;
            startupy++;
        }

        while(startdownx != 7 && startdowny != 0) {
            if(newBoard[startdownx][startdowny] != 'Q') {
                newBoard[startdownx][startdowny] = 'i';
            }
            startdownx++;
            startdowny--;
        }

        return newBoard;
    }

    public static void printBoard(char[][] Board) {
        StringBuilder myBoard = new StringBuilder();
            for (char[] row : Board) {
            StringBuilder rows = new StringBuilder();
            for (char number : row) {
                rows.append(number);
            }
            myBoard.append("\n");
            myBoard.append(rows.toString());
        }
        System.out.println(myBoard.toString());
    }


}

