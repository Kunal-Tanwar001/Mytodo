package com.example.prate.mytodo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Intent.ACTION_SEND;

public class ADDactivity extends AppCompatActivity {
Calendar cal=Calendar.getInstance();
int day=cal.get(Calendar.DAY_OF_MONTH);
int month=cal.get(Calendar.MONTH);
int year=cal.get(Calendar.YEAR);
int  minute=cal.get(Calendar.MINUTE);
int hour=cal.get(Calendar.HOUR);
String date=toString().valueOf(day)+"/"+toString().valueOf(month)+"/"+toString().valueOf(year);
String time=toString().valueOf(hour)+":"+toString().valueOf(minute);
EditText tittle,discription;
TextView timeshow,dateshow;
Button add;
Long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);
       tittle=findViewById(R.id.tittle);
       discription=findViewById(R.id.discription);
       timeshow=findViewById(R.id.time);
       dateshow=findViewById(R.id.date);
       add=findViewById(R.id.button);
        timeshow.setText(time);
        dateshow.setText(date);
       timeshow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               add1(hour,minute);
           }
       });
       dateshow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               add(day,month,year);
           }
       });

        Intent intent = getIntent();
        String action = intent.getAction();
        if(action == ACTION_SEND){
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);
            discription.setText(text);
            tittle.setText("Copied");
//            finish = true;
        }


    }
    public void add(int day,int month,int year){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yea, int mont, int dayOfMonth) {
               dateshow.setText(toString().valueOf(dayOfMonth)+"/"+toString().valueOf(mont)+"/"+toString().valueOf(yea));
            }
        },year,month,day);
        datePickerDialog.setTitle("Showing Date");
        datePickerDialog.show();
    }
    public void add1(int hour,int minute){
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
              timeshow.setText(toString().valueOf(hourOfDay)+":"+toString().valueOf(minute));

            }
        },hour,minute,true);
        timePickerDialog.show();
    }
    public void addbutton(View v){
         String s=tittle.getText().toString();

         String d=discription.getText().toString();

         String t=timeshow.getText().toString();
         String da=dateshow.getText().toString();


        Expensedatabase datas=Expensedatabase.getInstance(this);
        SQLiteDatabase db=datas.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(databasenames.Expense.col_titlle,s);
        contentValues.put(databasenames.Expense.col_date,da);
        contentValues.put(databasenames.Expense.col_time,t);
        contentValues.put(databasenames.Expense.col_discription,d);
        long id=  db.insert(databasenames.Expense.table_name,null,contentValues);


        Bundle b=new Bundle();
        b.putString("tittle",s);
        b.putString("discription",d);
        b.putString("time",t);
        b.putString("date",da);
        b.putLong("id",id);
        Intent intent=new Intent();
        intent.putExtras(b);



        Toast.makeText(this, "ToDo Created", Toast.LENGTH_SHORT).show();

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(this,MyReciever2.class);
        intent1.putExtra("ID1",id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent1,0);
        Log.i("Alarm", String.valueOf(cal.getTimeInMillis()));
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);

        long currentTime = System.currentTimeMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,currentTime + 5000,10000,pendingIntent);


//        Intent intent = new Intent();
//        intent.putExtra("Title",title);
//        intent.putExtra("Desc",desc);
//        intent.putExtra("Date",date);
//        intent.putExtra("Time",time);
//        intent.putExtra("DTCreated",dtCrerated);
        setResult(1,intent);
//        if(!finish) {
        finish();
//        }

    }
}
