package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pocket.db";

    public DBHelper(Context context) {super(context,DATABASE_NAME, factory:null, version:1); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE" + UserMaster.Users.TABLE_NAME + " (" +
                UserMaster.Users._ID + "INTEGER PRIMARY KEY," +
                UserMaster.Users.COLUMN_NAME_AMOUNT + "DOUBLE," +
                UserMaster.Users.COLUMN_NAME_TYPE + "TEXT," +
                UserMaster.Users.COLUMN_NAME_DATE + "DATE," +
                UserMaster.Users.COLUMN_NAME_NOTE + "TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInfo(String amount, String type, String date, String note) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_AMOUNT, amount);
        values.put(UserMaster.Users.COLUMN_NAME_TYPE, type);
        values.put(UserMaster.Users.COLUMN_NAME_DATE, date);
        values.put(UserMaster.Users.COLUMN_NAME_NOTE, note);

        long newRecId = db.insert(UserMaster.Users.TABLE_NAME, null, values);
    }


}
