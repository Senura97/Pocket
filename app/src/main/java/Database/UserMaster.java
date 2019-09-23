package Database;

import android.provider.BaseColumns;

import com.example.pocket.login;

public final class UserMaster {

    public UserMaster() {}

    public UserMaster(login login) {
    }

    protected static class Users implements BaseColumns{


        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BANK = "bank";
        public static final String COLUMN_NAME_ACCNO = "accountno";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_TYPE = "type";



        public static final String TABLE2_NAME = "user";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }







}
