package connect.gcm.akshay.compufest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    DatabaseHandler db;
    SQLiteDatabase database;

    ListView lstView;
    List<NotificationMessage> arr_memo = new ArrayList<>();
    private BroadcastReceiver mRegistrationBroadcastReciever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        db = new DatabaseHandler(this);
        database=db.getReadableDatabase();

        lstView = (ListView) findViewById(R.id.lstnotification);

        arr_memo = db.getAllContacts();
        NotificationAdaptor memoAdaptor = new NotificationAdaptor(getApplicationContext(), arr_memo);
        lstView.setAdapter(memoAdaptor);



        mRegistrationBroadcastReciever=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().endsWith(GCMRegistrationIntentService.REGISTRATION_SUCCESS))
                {
                    String token=intent.getStringExtra("token");
                 //   Toast.makeText(getApplicationContext(),"GCM Token: "+token,Toast.LENGTH_LONG).show();

                }
                else if(intent.getAction().endsWith(GCMRegistrationIntentService.REGISTRATION_ERROR))
                {
                   // Toast.makeText(getApplicationContext(),"GCM registration error",Toast.LENGTH_LONG).show();
                }
                else
                {

                }
            }
        };

        int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(ConnectionResult.SUCCESS!=resultCode)
        {
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                //Toast.makeText(NotificationActivity.this, "Google play service is not installed/enabled in the device...", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode,getApplicationContext());
            }
            else
            {
                //Toast.makeText(NotificationActivity.this, "This device does not support google play service", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Intent intent=new Intent(getApplicationContext(),GCMRegistrationIntentService.class);
            startService(intent);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Notification Activity ","onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReciever,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReciever,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Notification Activity","onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReciever);
    }
}
