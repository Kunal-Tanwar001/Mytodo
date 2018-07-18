package com.example.prate.mytodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static org.xmlpull.v1.XmlPullParser.TEXT;

public class Expensedatabase extends SQLiteOpenHelper {
    public final static String database_name="name";
    public static final int version=1;

    private static   Expensedatabase datanew ;

    public static Expensedatabase getInstance(Context context){
        if(datanew==null){
            datanew =new Expensedatabase(context.getApplicationContext());
        }
return datanew;
    }
    public Expensedatabase(Context context) {
        super(context, database_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        String expensesSql = "CREATE TABLE "+ databasenames.Expense.table_name +" ( " +
//                databasenames.Expense.col_id + " INTEGER PRIMARY KEY , " +
//                databasenames.Expense.col_titlle  + " TEXT , " +databasenames.Expense.col_time+" TEXT , " + databasenames.Expense.col_discription +" TEXT ,"+
//                databasenames.Expense.col_date + " TEXT );";

        String sql = "CREATE TABLE "+databasenames.Expense.table_name+" ( "+
                databasenames.Expense.col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+databasenames.Expense.col_time+" TEXT ,"+  databasenames.Expense.col_titlle  + "  TEXT ," + databasenames.Expense.col_discription +" TEXT ,"+
                databasenames.Expense.col_date + " TEXT )";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
