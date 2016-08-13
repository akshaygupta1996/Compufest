package connect.gcm.akshay.compufest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Akshay on 13-07-2016.
 */
public class GCMPushReceiverService extends GcmListenerService {

    DatabaseHandler db;
    SQLiteDatabase database;

    @Override
    public void onCreate() {

        db = new DatabaseHandler(this);
        database=db.getWritableDatabase();
        super.onCreate();
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {

        String message=data.getString("message");
        long id=db.addMessage(message);
        if(id<0)
        {
            //Toast.makeText(getApplicationContext(),"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
            Log.v("DATA","NOT INSERTED");
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"DATA INSERTED",Toast.LENGTH_LONG).show();
            Log.v("DATA","DATA INSERTED");
        }
        sendNotification(message);
    }
    private void sendNotification(String message)
    {
        Intent intent=new Intent(this,NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode=0;
        PendingIntent pendingIntent=PendingIntent.getActivity(this,requestCode,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("My GCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noBuilder.build());

    }
}
