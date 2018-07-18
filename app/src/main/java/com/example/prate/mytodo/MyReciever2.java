package com.example.prate.mytodo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.telecom.Call;

import com.example.prate.mytodo.R;

public class MyReciever2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        long id = intent.getLongExtra("ID1",-1);

        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myChannelId", "ToDoChannel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"myChannelId");
        builder.setContentTitle("ToDo");
        Expensedatabase openHelper = Expensedatabase.getInstance(context);
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String[] selectionArgs = {id+""};
        String[] cols = {databasenames.Expense.col_titlle};
        Cursor cursor = database.query(databasenames.Expense.table_name,null,databasenames.Expense.col_id+" = ?",selectionArgs,null,null,null);
        cursor.moveToNext();
        builder.setContentText(cursor.getString(cursor.getColumnIndex(databasenames.Expense.col_titlle)));
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        Intent intent1 = new Intent(context,editactivity.class);
        intent1.putExtra("ID2",id);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,2,intent1,0);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        manager.notify(1,notification);
    }   
}
