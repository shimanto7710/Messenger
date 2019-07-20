package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    int newTourParentId;

    String DB_PATH = null;
    private static String DB_NAME = "sample.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

//    public static final String COL_1 = "";
//    public static final String COL_2 = "NAME";
//    public static final String COL_3 = "SURNAME";
//    public static final String COL_4 = "MARKS";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
//        this.DB_PATH= String.valueOf(context.getDatabasePath(DB_NAME));

//        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context,DB_NAME);
//        SQLiteDatabase database = helper.getReadableDatabase();
//        String filePath = database.getPath();
//        database.close()

        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.d("xxx Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        // myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }


    public void debug() {
        openDataBase();
        Cursor cursor = myDataBase.rawQuery("select * from user_info;", null);
        Log.d("cccc", "count: " + String.valueOf(cursor.getCount()));
    }


    /**
     * @param columns        column which is need to get
     * @param selection      attribute to compare
     * @param matchSomething value to match with column
     * @return cursor
     */
    public Cursor getData(String[] columns, String selection, String[] matchSomething,String tableName) {
//        String tableName = t;
//        String[] columns ={"quantity", "price","owner"};
        openDataBase();

//        Cursor findEntry = db.query("sku_table", columns, "owner=? and price=?", new String[] { owner, price }, null, null, null);
        Cursor cursor = myDataBase.query(tableName, columns, selection, matchSomething, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor;
        }

        return null;
    }

    public int getSize(String sql) {
        openDataBase();
        final Cursor cursor = myDataBase.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    public long writeData(ContentValues values,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserting Row
        long aa=db.insert(tableName, null, values);
        db.close(); // Closing database connection
        return aa;
    }

    public void updateData(ContentValues values, String whereClause, String[] whereArgs) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.update("bigData", values, whereClause, whereArgs);
        db.close();

    }


}


