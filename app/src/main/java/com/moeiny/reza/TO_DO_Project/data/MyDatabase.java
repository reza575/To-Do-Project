package com.moeiny.reza.TO_DO_Project.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME="ToDoApp";
    public static final int DB_VERSION=1;
    public static final String TBL_NAME="todolist";
    public static final String COL_ID="id";
    public static final String COL_TITLE="title";
    public static final String COL_DESC="description";
    public static final String COL_DATE="date";
    public static final String COL_TIME="time";
    public static final String COL_MARK="mark";

    public static final String QUERY="CREATE TABLE IF NOT EXISTS "+TBL_NAME+"("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_TITLE+" TEXT,"+
            COL_DESC+" TEXT,"+
            COL_DATE+" TEXT,"+
            COL_TIME+" TEXT,"+
            COL_MARK+" TEXT);";

    Context context;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(QUERY);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public long addInfo(String title, String desc, String date,String time,String mark){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_TITLE,title);
        contentValues.put(COL_DESC,desc);
        contentValues.put(COL_DATE,date);
        contentValues.put(COL_TIME,time);
        contentValues.put(COL_MARK,mark);

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long result=sqLiteDatabase.insert(TBL_NAME,null,contentValues);
        if(result>0){
           return result;
        }else{
            return result;
        }
    }

    public Cursor getInfos(){
        String query="SELECT  * FROM "+TBL_NAME+"" ;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,null);
    }

    public Cursor getSomeData(){
        String mark="1";
        String query="SELECT * FROM "+TBL_NAME+" WHERE "+COL_MARK+" = ?";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(mark)});
    }

    public Cursor getSomeDatabyid(Integer id){

        String query="SELECT * FROM "+TBL_NAME+" WHERE "+COL_ID+" = ?";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(id)});
    }

    public Cursor getSearchData(String title){
        String search="%"+title.trim()+"%";
        String query="SELECT * FROM "+TBL_NAME+" WHERE "+COL_TITLE+" LIKE ?";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,new String[]{search});
    }

    public void updateRow(Integer id,ContentValues values){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        sqLiteDatabase.update(TBL_NAME,values,COL_ID+" = ?",new String[]{String.valueOf(id)});
    }

    public void deleteRow(Integer id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TBL_NAME,COL_ID+" = ?",new String[]{String.valueOf(id)});
    }


}
