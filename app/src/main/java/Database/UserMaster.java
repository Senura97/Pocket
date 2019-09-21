package Database;

import android.provider.BaseColumns;

public final class UserMaster {

    private UserMaster() {}

    protected static class Users implements BaseColumns{


        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BANK = "bank";
        public static final String COLUMN_NAME_ACCNO = "accountno";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_TYPE = "type";

    }

}
