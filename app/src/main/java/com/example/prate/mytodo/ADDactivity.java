package com.example.prate.mytodo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.io.Serializable;
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
EditText tittle,discription,timeshow,dateshow;
Button add;


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

        Bundle b=new Bundle();
        b.putString("tittle",s);
        b.putString("discription",d);
        b.putString("time",t);
        b.putString("date",da);
        Intent intent=new Intent();
        intent.putExtras(b);
        setResult(1,intent);
        finish();

    }
}
