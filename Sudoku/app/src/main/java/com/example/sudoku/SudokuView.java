package com.example.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SudokuView extends SurfaceView implements SurfaceHolder.Callback {
    private int size = 9;
    private float cellSizePixels = 0F;
    private int selectedCol = 5;
    private int selectedRow = 5;
    private float boarderSize = 10F;
    private float gridSize = 4F;
    private float textSize = 70F;
    Paint border = new Paint();
    Paint grid = new Paint();
    Paint text = new Paint();
    Paint selected = new Paint();
    private int num;
    private int[][] board = new int[9][9];
    private int[][] black = new int[9][9];
    private int[][] red = new int[9][9];
    private int[][] full = new int[9][9];
    ArrayList<String> noEdit = new ArrayList<>();


    public SudokuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true); // make sure we get key events
        setPaints();
    }

    public void setPaints(){
        border.setStyle(Paint.Style.STROKE);
        border.setColor(Color.BLACK);
        border.setStrokeWidth(boarderSize);
        grid.setStyle(Paint.Style.STROKE);
        grid.setColor(Color.BLACK);
        grid.setStrokeWidth(gridSize);
        selected.setStyle(Paint.Style.FILL_AND_STROKE);
        selected.setColor(Color.parseColor("#c9ecf5"));
        text.setStyle(Paint.Style.FILL_AND_STROKE);
        text.setStrokeWidth(4F);
        text.setTextSize(textSize);
    }

    @Override
    public void onDraw(Canvas canvas){
        cellSizePixels = (float) (getWidth()-50)/size;
        fillCells(canvas);
        setNumbers(canvas);
        canvas.drawRect(15F, 15F, (float) getWidth()-15F, getWidth() -15F, border);

        for(int i = 1; i < size; i++) {
            if(i == 3 || i == 6){
                grid.setStrokeWidth(8F);
            }
            canvas.drawLine(20F, 10F + (cellSizePixels + 2F) * i, getWidth()-15F, 10F + (cellSizePixels + 2F) * i, grid);
            canvas.drawLine(10F + (cellSizePixels + 2F) * i, 20F, 10F + (cellSizePixels + 2F) * i, getWidth()-15F, grid);
            grid.setStrokeWidth(gridSize);
        }
    }

    public void startBoard(int[][] start){
        board = start;
    }

    private void fillCells(Canvas canvas){
        if(selectedCol != -1 && selectedRow != -1){
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    if(i == selectedCol && j == selectedRow){
                        fillCell(canvas, i, j, selected);
                    }
                }
            }
        }
    }

    public void restart(){
        black = new int[9][9];
        red = new int[9][9];
        full = new int[9][9];
        invalidate();
    }

    private void fillCell(Canvas canvas, int row, int col, Paint paint){
        canvas.drawRect(15F + row * (2F + cellSizePixels), 15F + col * (2F + cellSizePixels), 15F + (row + 1) * (2F + cellSizePixels), 15F + (1 + col) * (2F + cellSizePixels), paint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            handleEvent(event.getX(), event.getY());

        }
        return true;
    }

    public void handleEvent(float x, float y){
        selectedCol = (int) (x /cellSizePixels);
        selectedRow = (int) (y /cellSizePixels);
        invalidate();
    }

    public void onNumberClick(int number, int color){
        num = number;
        if(color == 1){
            black[selectedRow][selectedCol] = num;
            if(red[selectedRow][selectedCol] !=0){
                red[selectedRow][selectedCol] = 0;
            }
        }
        if(color == 2){
            red[selectedRow][selectedCol] = num;
            if(black[selectedRow][selectedCol] !=0){
                black[selectedRow][selectedCol] = 0;
            }
        }
        invalidate();
    }

    public void onRemoveClick(){
        black[selectedRow][selectedCol] = 0;
        red[selectedRow][selectedCol] = 0;
        invalidate();

    }

    public int[][] checkBoard(){
        for(int i = 0; i < full.length; i++){
            for(int j = 0; j < full.length; j++){
                if(board[i][j] != 0){
                    full[i][j] = board[i][j];
                }
                if(black[i][j] != 0){
                    full[i][j] = black[i][j];
                }
                if(red[i][j] != 0){
                    full[i][j] = red[i][j];
                }
            }
        }
        return full;
    }

    public int[][] makeBoard(){
        return black;
    }

    public void setNumbers(Canvas canvas){
        for(int i = 0; i < black.length; i++){
            for(int j = 0; j < black.length; j++){
                if(board[i][j] != 0){
                    text.setColor(Color.BLUE);
                    canvas.drawText(Integer.toString(board[i][j]), 15F + j * cellSizePixels + cellSizePixels/3, 15F + i * cellSizePixels + 4*cellSizePixels/5, text);
                    black[i][j] = 0;
                    red[i][j] = 0;
                }
                if(black[i][j] != 0){
                    text.setColor(Color.BLACK);
                    canvas.drawText(Integer.toString(black[i][j]), 15F + j * cellSizePixels + cellSizePixels/3, 15F + i * cellSizePixels + 4*cellSizePixels/5, text);
                }
                if(red[i][j] != 0){
                    text.setColor(Color.RED);
                    canvas.drawText(Integer.toString(red[i][j]), 15F + j * cellSizePixels + cellSizePixels/3, 15F + i * cellSizePixels + 4*cellSizePixels/5, text);
                }
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void setup(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] != 0){
                    String xy = i + "-" + j;
                    noEdit.add(xy);
                }
            }
        }
    }
}
