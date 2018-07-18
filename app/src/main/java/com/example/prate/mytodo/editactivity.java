package com.example.prate.mytodo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class editactivity extends AppCompatActivity {
EditText tittle,discription;
TextView time,date;
Button save;
    Calendar newCalendar = Calendar.getInstance();
    int month = newCalendar.get(Calendar.MONTH);
    int year = newCalendar.get(Calendar.YEAR);
    int day = newCalendar.get(Calendar.DAY_OF_MONTH);
    int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
    int min = newCalendar.get(Calendar.MINUTE);
long id;
String tiitl,discriptio,tim,dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editactivity);
        tittle = findViewById(R.id.tittle);
        discription = findViewById(R.id.discription);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
         save=findViewById(R.id.button);
      Log.d("editactivity","nhi chala");
      Intent intent = getIntent();
        id= intent.getLongExtra("IDMERI", 0);
        Expensedatabase openHelper = Expensedatabase.getInstance(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String[] colum = {id + ""};
        Cursor cursor = db.query(databasenames.Expense.table_name, null, databasenames.Expense.col_id + " = ?", colum, null, null, null);

        while(cursor.moveToNext()) {
             tiitl = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_titlle));
             discriptio = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_discription));
             tim = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_time));
             dat = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_date));
        }
       cursor.close();
        tittle.setText(tiitl);
        discription.setText(discriptio);
        time.setText(tim);
        date.setText(dat);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtime(hour, min);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(day,month,year);
            }
        });
    }


        public void add(int day,int month,int year){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int yea, int mont, int dayOfMonth) {
                    date.setText(toString().valueOf(dayOfMonth)+"/"+toString().valueOf(mont)+"/"+toString().valueOf(yea));
                }
            },year,month,day);
            datePickerDialog.setTitle("Showing Date");
            datePickerDialog.show();
        }
        public void addtime(int hour,int minute){
            TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    time.setText(toString().valueOf(hourOfDay)+":"+toString().valueOf(minute));

                }
            },hour,minute,true);
            timePickerDialog.show();
        }
        public void addbutton(View v){

            String s=tittle.getText().toString();

            String d=discription.getText().toString();

            String t=time.getText().toString();
            String da=date.getText().toString();
            Expensedatabase ex= Expensedatabase.getInstance(this);
            SQLiteDatabase db=ex.getWritableDatabase();
            ContentValues contentValues=new ContentValues();

            contentValues.put(databasenames.Expense.col_titlle,s);
            contentValues.put(databasenames.Expense.col_discription,d);
            contentValues.put(databasenames.Expense.col_time,t);
            contentValues.put(databasenames.Expense.col_date,da);
            String[] colum = {id + ""};
            db.update(databasenames.Expense.table_name,contentValues,databasenames.Expense.col_id+" = ?",colum);

             Intent intent=new Intent();
             Bundle b=new Bundle();
           b.putLong("ff",id);
           intent.putExtras(b);
            setResult(4,intent);
            finish();

        }

}
