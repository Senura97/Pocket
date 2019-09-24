package Database;

import  android.provider.BaseColumns;

public final class UserMaster {

    private UserMaster(){}

    protected static class Users implements BaseColumns{

        public static final String TABLE_NAME = "expenses";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_NOTE = "note";


    }


}
