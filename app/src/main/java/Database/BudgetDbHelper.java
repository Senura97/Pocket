package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pocket.Budget;

import java.util.ArrayList;

public class BudgetDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Pocket.db";
    public static final String TABLE_NAME="budget_table";
    public static final String COL_1="ID";
    public static final String COL_2="DATE";
    public static final String COL_3="CATEGORY";
    public static final String COL_4="AMMOUNT";

    public BudgetDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY,DATE TEXT,CATEGORY TEXT,AMMOUNT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String date,String category,String ammount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, category);
        contentValues.put(COL_4,ammount);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        Log.i("BUDGET_DB", "==============================================" + toString().valueOf(result));
        if (result == -1) {
            return false;
        } else
            return true;


    }


    public  Budget getBudget(int bId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                COL_1,
                COL_2,
                COL_3,
                COL_4
        };

        String selection = COL_1 + "=?";
        String[] selectionArgs = {String.valueOf(   bId)};

        Cursor cursor = sqLiteDatabase.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        cursor.moveToNext();

        Budget b = new Budget();
        b.id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_1));
        b.date = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
        b.category= cursor.getString(cursor.getColumnIndexOrThrow(COL_3));
        b.ammount= cursor.getString(cursor.getColumnIndexOrThrow(COL_4));




        return b;


    }

    public ArrayList<Budget> getAllBudgets(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] select = {

                COL_1,COL_2,COL_3,COL_4

        };

        Cursor cursor = sqLiteDatabase.query(
                TABLE_NAME,
                select,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Budget> budgetList = new ArrayList<>();


        while (cursor.moveToNext()){
            Budget b = new Budget();
            b.id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_1));
            b.date = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
            b.category = cursor.getString(cursor.getColumnIndexOrThrow(COL_3));
            b.ammount = cursor.getString(cursor.getColumnIndexOrThrow(COL_4));


            budgetList.add(b);
        }

        return budgetList;
    }



    public boolean updateBudget(String date,String category,String ammount,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,date);
        contentValues.put(COL_3,category);
        contentValues.put(COL_4,ammount);

        String selection = COL_1 + "=?";
        String[] selectionArgs = {String.valueOf(id)};


        int count = sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                selection,
                selectionArgs);
        Log.i("BUDGET_DB", "==============================================" + toString().valueOf(count));

        if(count>0)
            return true;

        return false;


    }



    public boolean deleteBudget(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String selection = COL_1 + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        int count = sqLiteDatabase.delete(TABLE_NAME,
                selection,
                selectionArgs);


        if(count>0)
            return true;

        return false;




    }


}

