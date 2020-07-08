package id.ac.umn.uts_14910;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract(){}

    public class FavEntry implements BaseColumns{
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_ASIN = "ASIN";
        public static final String COLUMN_GROUP1 = "GROUP1";
        public static final String COLUMN_FORMAT = "FORMAT";
        public static final String COLUMN_TITLE = "TITLE";
        public static final String COLUMN_AUTHOR = "AUTHOR";
        public static final String COLUMN_PUBLISHER = "PUBLISHER";
    }
}
