package id.ac.umn.uts_14910;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Size;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BookDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "books.db";
    private static String DB_PATH;
    private SQLiteDatabase db;
    private Context context;
    private static final int DB_VERSION = 1;
    private static BookDbHelper sInstance;

//udah ditaruh dbContract
//    private static final String TABLE_BOOKS = "books";
//    public static final String COLUMN_ASIN = "ASIN";
//    public static final String COLUMN_GROUP1 = "GROUP1";
//    public static final String COLUMN_FORMAT = "FORMAT";
//    public static final String COLUMN_TITLE = "TITLE";
//    public static final String COLUMN_AUTHOR = "AUTHOR";
//    public static final String COLUMN_PUBLISHER = "PUBLISHER";

    //=========================FAVORITES====================================

    private static final String SQL_CREATE_FAV_QUERY =
            "CREATE TABLE IF NOT EXISTS " + DBContract.FavEntry.TABLE_NAME + "(" +
                    DBContract.FavEntry.COLUMN_ASIN + " TEXT, " +
                    DBContract.FavEntry.COLUMN_GROUP1 + " TEXT, " +
                    DBContract.FavEntry.COLUMN_FORMAT + " TEXT, " +
                    DBContract.FavEntry.COLUMN_TITLE + " TEXT, " +
                    DBContract.FavEntry.COLUMN_AUTHOR + " TEXT, " +
                    DBContract.FavEntry.COLUMN_PUBLISHER + " TEXT " +
                    ")";

    private static final  String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " +DBContract.FavEntry.TABLE_NAME;


//    private static final String SQL_DUMMY_ENTRIES =
//            "INSERT INTO " + DBContract.FavEntry.TABLE_NAME + " (" +
//                    DBContract.FavEntry.COLUMN_ASIN + "," +
//                    DBContract.FavEntry.COLUMN_GROUP1+ "," +
//                    DBContract.FavEntry.COLUMN_FORMAT+ "," +
//                    DBContract.FavEntry.COLUMN_TITLE+ "," +
//                    DBContract.FavEntry.COLUMN_AUTHOR+ "," +
//                    DBContract.FavEntry.COLUMN_PUBLISHER +
//                    ") VALUES " +
//                    "('', '', '', '', '', '')";

    public long addFavorites(String asin, String group1, String format, String title, String author, String publisher){
        SQLiteDatabase db = sInstance.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.FavEntry.COLUMN_ASIN, asin);
        contentValues.put(DBContract.FavEntry.COLUMN_GROUP1, group1);
        contentValues.put(DBContract.FavEntry.COLUMN_FORMAT, format);
        contentValues.put(DBContract.FavEntry.COLUMN_TITLE, title);
        contentValues.put(DBContract.FavEntry.COLUMN_AUTHOR, author);
        contentValues.put(DBContract.FavEntry.COLUMN_PUBLISHER, publisher);

        long result = db.insert(
                DBContract.FavEntry.TABLE_NAME, null, contentValues
        );

        if(result == -1){

        }

        else {

        }

        return result;
    }

    public Cursor getDataFav(){
        SQLiteDatabase db2 = sInstance.getReadableDatabase();

        String[] projection = {
                "ASIN",
                "GROUP1",
                "FORMAT",
                "TITLE",
                "AUTHOR",
                "PUBLISHER"
        };

        String selection = "";
        String[] selectionArgs = {};

        Cursor cursor2 = db2.query(
                "favorites",

                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor2;
    }

//    public int deleteLastFavData(){
//        SQLiteDatabase db = sInstance.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(
//                "SELECT MAX(" +DBContract.FavEntry.COLUMN_TITLE + ") " +
//                        "FROM " + DBContract.FavEntry.TABLE_NAME, null
//        );
//
//        int maxTitle;
//        if(cursor.moveToNext()){
//            maxTitle = cursor.getInt(3);
//        }
//        else {
//            return -1;
//        }
//
//        cursor.close();
//
//        db = sInstance.getWritableDatabase();
//        int result = db.delete(
//                DBContract.FavEntry.TABLE_NAME, "TITLE = ?", new String[] {String.valueOf(maxTitle)}
//        );
//
//        return result;
//    }

    //=========================FAVORITES====================================

    public BookDbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        String packageName = context.getPackageName();

        DB_PATH = "/data/data/" + packageName + "/databases/";
        this.context = context;

        openDatabase();

        db.execSQL(SQL_CREATE_FAV_QUERY);
    }

    public SQLiteDatabase openDatabase(){
        String path = DB_PATH + DB_NAME;
        if(db == null){
            createDatabase();
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }
        return db;
    }

    public void createDatabase(){
        boolean dbExist = checkDatabase();
        if(!dbExist){
            this.getReadableDatabase();
            try {
                copyDatabase();
            }
            catch (IOException e){
                Log.e(this.getClass().toString(), "Copying Error");
                throw new Error("Error copying databasse!");
            }
        }
        else {
            Log.i(this.getClass().toString(), "Database already exist!");
        }
    }

    private boolean checkDatabase(){
        String path = DB_PATH + DB_NAME;
        File dbFile = new File(path);
        Log.d("debugdb", "value : " +path);
        return dbFile.exists();
    }

    private void copyDatabase() throws IOException{
        InputStream externalDBStream = context.getAssets().open("databases/" + DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream localDBStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = externalDBStream.read(buffer)) > 0){
            localDBStream.write(buffer, 0, bytesRead);
        }
        localDBStream.flush();
        localDBStream.close();
        externalDBStream.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_FAV_QUERY);
        //db.execSQL(SQL_DUMMY_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }

    public Cursor getData(){
        SQLiteDatabase db1 = sInstance.getReadableDatabase();

        String[] projection = {
                "ASIN",
                "GROUP1",
                "FORMAT",
                "TITLE",
                "AUTHOR",
                "PUBLISHER"
        };

        String selection = "";
        String[] selectionArgs = {};

        Cursor cursor = db1.query(
                "books",

                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor;
    }

    public static synchronized BookDbHelper getsInstance(Context context){
        if(sInstance == null){
            sInstance = new BookDbHelper(context.getApplicationContext());
        }

        return sInstance;
    }
}
