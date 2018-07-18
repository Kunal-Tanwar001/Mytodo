package com.example.prate.mytodo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
 ListView l1;
 int post;
    Adapter ad;
    Expensedatabase db;
 ArrayList<Expense> item=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1=findViewById(R.id.list_item);
        Expensedatabase toDoOpenHelper = Expensedatabase.getInstance(this);
        SQLiteDatabase database = toDoOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(databasenames.Expense.table_name,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_titlle));
            String desc = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_discription));
            String date = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_date));
            String time = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_time));


            long id = cursor.getLong(cursor.getColumnIndex(databasenames.Expense.col_id));

            Expense toDo = new Expense(title,desc,date,time);
            toDo.setId(id);
            item.add(toDo);

        }
        cursor.close();
        ad=new Adapter(this,item);
        l1.setAdapter(ad);
      l1.setOnItemClickListener(this);
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Expense toDo = item.get(i);
                final int pos = i;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setView();
                builder.setTitle("Confirm Delete");
                builder.setMessage("Are you sure you want to delete ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        db = Expensedatabase.getInstance(MainActivity.this);
                       SQLiteDatabase database = db.getWritableDatabase();
                        String[] selectionArgs = {toDo.getId()+""};
                        database.delete(databasenames.Expense.table_name,databasenames.Expense.col_id+" = ?",selectionArgs);



                        item.remove(pos);
                        ad.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Deletion Cancelled",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0 && resultCode==1){


        Bundle b=data.getExtras();
        String ti=b.getString("tittle");
        Log.d("mainactivity","tittle");
        String d=b.getString("discription");
        String da=b.getString("date");
        String t=b.getString("time");
       Long id=b.getLong("id");
       Expense ex=new Expense(ti,d,t,da);
       ex.setId(id);
       item.add(ex);

        ad.notifyDataSetChanged();


        }
        if(requestCode==3 && resultCode==4){
        Bundle b=data.getExtras();
        Long id=b.getLong("ff");
            Expensedatabase openHelper = Expensedatabase.getInstance(this);
            SQLiteDatabase db = openHelper.getWritableDatabase();
            String[] colum = {id + ""};
            Cursor cursor = db.query(databasenames.Expense.table_name, null, databasenames.Expense.col_id + " = ?", colum, null, null, null);

          while(cursor.moveToNext()) {
              String tiitl = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_titlle));
              String discriptio = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_discription));
              String tim = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_time));
              String dat = cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_date));
              Expense ex = new Expense(tiitl, discriptio, tim, dat);
              ex.setId(id);
              item.set(post, ex);
              ad.notifyDataSetChanged();
          }
          cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int items=item.getItemId();
        if(items==R.id.plus){
            Intent intent=new Intent(this,ADDactivity.class);

            startActivityForResult(intent,0);

        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Expense ex=item.get(i);
          post=i;
      Intent intent=new Intent(MainActivity.this,editactivity.class);

      intent.putExtra("IDMERI",ex.getId());
      startActivityForResult(intent,3);

    }
}
