package com.example.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    static final String TABLE_BOARD = "boards";
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "board";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME="BooksDb";
    static final int DATABASE_VERSION=1;
    static final String DATABASE_CREATE1 = "create table " + TABLE_BOARD
            + " ("+KEY_ROWID+" integer primary key autoincrement, "
            + KEY_NAME +" text unique not null);";

    static  final String GET_ALL_BOARDS =
            "select " + KEY_NAME + " from " + TABLE_BOARD;

    private Context context;

    public DatabaseManager(Context context) throws Exception {
        super(context,
                /*db name=*/ DATABASE_NAME,
                /*cursorFactory=*/ null,
                /*db version=*/DATABASE_VERSION);
        //     Toast.makeText(context, "Inn i databasekonstruktøren", Toast.LENGTH_LONG).show();
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db", "onCreate");
        db.execSQL(DATABASE_CREATE1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        Log.d("db", "onUpdate");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOARD);
        // re-create the table
        onCreate(db);
    }
    public void clean() {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 0, 0);
        db.close();
    }
    private long insertBoard(String board, SQLiteDatabase db) {
        ContentValues initalValues = new ContentValues();
        initalValues.put(KEY_NAME, board);
        long id = db.insert(TABLE_BOARD, null, initalValues);
        return id;
    }

    public long insert(String board){
        SQLiteDatabase db = null;
        db = this.getWritableDatabase();
        Cursor cursor =
                db.query(true, TABLE_BOARD, new String[]{KEY_ROWID, KEY_NAME}, KEY_NAME + "='" + board + "'", null, null, null, null, null);
        long board_id;
        if (cursor == null || cursor.getCount() == 0) {
            board_id = insertBoard(board,db);
        } else {
            cursor.moveToFirst();
            board_id = cursor.getLong(0);
        }

        return board_id;
    }


    public ArrayList<String> getAllBoards() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_BOARD, new String[]{KEY_ROWID, KEY_NAME}, null, null, null, null, null, null);
        ArrayList<String> res = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                res.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        db.close();
        return res;
    }

    public ArrayList<String> getAllBoardss() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.rawQuery(GET_ALL_BOARDS, new String[]{});
        ArrayList<String> res = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                res.add(cursor.getString(0) + ": " + cursor.getString(1));
                cursor.moveToNext();
            }
        }
        db.close();
        return res;
    }
}

