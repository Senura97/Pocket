package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static Database.UserMaster.Users.COLUMN_NAME_AMOUNT;
import static Database.UserMaster.Users.COLUMN_NAME_NAME;
import static Database.UserMaster.Users.COLUMN_NAME_TYPE;
import static Database.UserMaster.Users.TABLE_NAME;
import static android.provider.BaseColumns._ID;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pocket.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE "+ TABLE_NAME+ " (" +
                UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                UserMaster.Users.COLUMN_NAME_NAME + " TEXT," +
                UserMaster.Users.COLUMN_NAME_BANK + " TEXT," +
                UserMaster.Users.COLUMN_NAME_ACCNO + " INTEGER," +
                UserMaster.Users.COLUMN_NAME_AMOUNT + " DOUBLE," +
                UserMaster.Users.COLUMN_NAME_TYPE + " TEXT)" ;

        db.execSQL(SQL_CREATE_ENTRIES);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addInfo(String aname, String bank, String ano, String amt, String type){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_NAME, aname);
        values.put(UserMaster.Users.COLUMN_NAME_BANK, bank);
        values.put(UserMaster.Users.COLUMN_NAME_ACCNO, ano);
        values.put(UserMaster.Users.COLUMN_NAME_AMOUNT, amt);
        values.put(UserMaster.Users.COLUMN_NAME_TYPE, type);

        long newRowId = db.insert(TABLE_NAME,null,values);

        if(newRowId >= 1)
            return true;
        else
            return false;
    }

    // Get Account Details
    public ArrayList<HashMap<String, String>> GetAccounts(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> accList = new ArrayList<>();
        String query = "SELECT name, type, amount FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> acc = new HashMap<>();
            acc.put("name",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME)));
            acc.put("type",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE)));
            acc.put("amount", String.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_NAME_AMOUNT))));
            accList.add(acc);
        }
        return  accList;
    }

    // Get User  Account Details based on accountid
    public ArrayList<HashMap<String, String>> GetAccountByUserId(int accid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> accList = new ArrayList<>();
        String query = "SELECT name,type,amount FROM "+ TABLE_NAME;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NAME_NAME,COLUMN_NAME_TYPE,COLUMN_NAME_AMOUNT}, _ID+ "=?",new String[]{String.valueOf(_ID)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> acc = new HashMap<>();
            acc.put("name",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME)));
            acc.put("type",cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE)));
            acc.put("amount", String.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_NAME_AMOUNT))));
            accList.add(acc);
        }
        return  accList;
    }




}
