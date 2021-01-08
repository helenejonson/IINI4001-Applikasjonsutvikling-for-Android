package com.example.sudoku;

import java.util.ArrayList;

public class SudokuGameBoard {
    private String lvl;
    private int [][] board = new int[9][9];

    public SudokuGameBoard(){}

    public void setBoard(int selectedRow, int selectedCol, int val) {
        board[selectedRow][selectedCol] = val;
    }

    public void setFullBoard(int[][] myBoard){
        this.board = myBoard;
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean checkBoard(){
        boolean check = true;
        int checks = 0;

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 0){
                    check = false;
                }
            }
        }

        while (checks < 2 && check){
            int iCounter = 0;
            int jCounter = 0;
            while(iCounter < board.length && check){
                ArrayList<Integer> numbers = new ArrayList<>();
                while (jCounter < board[0].length && check){
                    int numberNow = -1;
                    if(checks == 0){
                        numberNow = board[iCounter][jCounter];
                    }else if(checks == 1){
                        numberNow = board[jCounter][iCounter];
                    }
                    for(int i = 0; i < numbers.size(); i++){
                        if(numberNow == numbers.get(i)){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        numbers.add(numberNow);
                    }
                    jCounter++;
                }
                jCounter = 0;
                iCounter++;
            }
            if(iCounter == 9){
                checks++;
            }
        }

        int square = 0;
        while (square < 9 && check){
            int row;
            int col;
            if(square == 0 || square == 1 || square == 2){
                row = 0;
            }else if (square == 3 || square == 4 || square == 5){
                row = 3;
            }else{
                row = 6;
            }

            if(square == 0 || square == 3 || square == 6){
                col = 0;
            }else if (square == 1 || square == 4 || square == 7){
                col = 3;
            }else{
                col = 6;
            }
            int rowNow = row;
            int colNow = col;
            int stepRow = rowNow + 3;
            int stepCol = colNow + 3;
            ArrayList<Integer> numbers = new ArrayList<>();
            while(rowNow < stepRow && check){
                while(colNow < stepCol && check){
                    int numberNow = board[rowNow][colNow];
                    colNow++;
                    for(int i = 0; i < numbers.size(); i++){
                        if(numberNow == numbers.get(i)){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        numbers.add(numberNow);
                    }
                }
                colNow = col;
                rowNow++;
            }
            if(rowNow == 3 || rowNow == 6 || rowNow == 9){
                square++;
            }
        }
        return check;
    }
}
