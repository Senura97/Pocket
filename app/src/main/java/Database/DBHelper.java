package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static Database.UserMaster.Users.COLUMN_NAME_AMOUNT;
import static Database.UserMaster.Users.COLUMN_NAME_EMAIL;
import static Database.UserMaster.Users.COLUMN_NAME_NAME;
import static Database.UserMaster.Users.COLUMN_NAME_PASSWORD;
import static Database.UserMaster.Users.COLUMN_NAME_TYPE;
import static Database.UserMaster.Users.TABLE2_NAME;
import static Database.UserMaster.Users.TABLE_NAME;
import static android.provider.BaseColumns._ID;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pocket.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 2);
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

        String SQL_CREATE_ENTRIES2 = "CREATE TABLE "+ TABLE2_NAME+ " (" +
                UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_EMAIL + " VARCHAR," +
                COLUMN_NAME_PASSWORD + " VARCHAR)" ;

        db.execSQL(SQL_CREATE_ENTRIES2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add account info
    public boolean addAccountInfo(String aname, String bank, String ano, String amt, String type){
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

    //add user login info
    public boolean addUserInfo(String uemail, String upass){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put(UserMaster.Users.COLUMN_NAME_EMAIL, uemail);
        values1.put(UserMaster.Users.COLUMN_NAME_PASSWORD, upass);

        long newRowId1 = db.insert(TABLE2_NAME,null,values1);

        if(newRowId1 > 0)
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

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                UserMaster.Users._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_NAME_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE2_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                UserMaster.Users._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_NAME_EMAIL + " = ?" + " AND " + COLUMN_NAME_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE2_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();


        if (cursorCount > 0) {
            System.out.println("true");
            cursor.close();
            db.close();
            return true;
        }
        else {
            System.out.println("f");
            cursor.close();
            db.close();
            return false;
        }


    }

}
