package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.UserMaster.Users.COLUMN_NAME_AMOUNT;
import static Database.UserMaster.Users.COLUMN_NAME_DATE;
import static Database.UserMaster.Users.COLUMN_NAME_TYPE;
import static Database.UserMaster.Users.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pocket.db";

    public DBHelper(Context context) {super(context,DATABASE_NAME, null, 1); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_TYPE + " TEXT," +
                COLUMN_NAME_AMOUNT + " DOUBLE," +
                COLUMN_NAME_DATE + " DATE," +
                UserMaster.Users.COLUMN_NAME_NOTE + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addInfo(String extype, String examount, String exdate, String exnote) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TYPE, extype);
        values.put(COLUMN_NAME_AMOUNT, examount);
        values.put(COLUMN_NAME_DATE, exdate);
        values.put(UserMaster.Users.COLUMN_NAME_NOTE, exnote);

        long newRecId = db.insert(TABLE_NAME, null,values);

        if(newRecId >= 1)
            return true;
        else
            return false;
    }


    // Get Account Details
    public ArrayList<HashMap<String, String>> GetExpenses(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> expList = new ArrayList<>();
        String query = "SELECT type, date, amount FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> expenses = new HashMap<>();
            expenses.put("type",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE)));
            expenses.put("date",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));
            expenses.put("amount", String.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_NAME_AMOUNT))));
            expList.add(expenses);
        }
        return  expList;
    }


}
