package com.techpalle.contentproviderexp1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private MyHelper myHelper;

    // URI - To- TABLE MAPPING LOGIC

    private static UriMatcher uriMatcher = new UriMatcher(-1);
    static {
        uriMatcher.addURI("com.techpalle.android","studenttable",1);
    }

    // end of mapping logic

    public class MyHelper extends SQLiteOpenHelper{


        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table student(_id integer primary key, sname text, ssub text);");



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case 1:
                // Means client is asking to insert into student table

                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                sqLiteDatabase.insert("student", null, values);
                break;
            default:
                // Invalid table
                break;
        }
        return null;
    }

    @Override
    public boolean onCreate() {

        myHelper = new MyHelper(getContext(), "techpalle.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch(uriMatcher.match(uri)){
            case 1:
                Cursor c = null;
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                c = sqLiteDatabase.query("student",null,null,null,null,null,null);
                return c;
            default:
                // Means invalid table
                break;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
