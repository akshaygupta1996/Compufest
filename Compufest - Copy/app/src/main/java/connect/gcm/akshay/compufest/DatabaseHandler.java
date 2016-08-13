package connect.gcm.akshay.compufest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Akshay on 23-07-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "gcm_database";

    // Contacts table name
    private static final String TABLE_CONTACTS = "gcm_info";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "gcm_message";
    private static final String KEY_TIME="gcm_time";
    //private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " VARCHAR(225), "+KEY_TIME+" VARCHAR(100));";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    long addMessage(String message) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String strDate = sdf.format(c.getTime());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, message);// Contact Name
        values.put(KEY_TIME,strDate);
        // values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
        Log.v("DATE",strDate);
        // Inserting Row
        long i=db.insert(TABLE_CONTACTS, null, values);
        db.close();
        return i;// Closing database connection
    }


    // Getting All Contacts
    public List<NotificationMessage> getAllContacts() {
        List<NotificationMessage> notificationList = new ArrayList<NotificationMessage>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                String nowTime = sdf.format(c.getTime());
                String messageTime=cursor.getString(2);

                Date nowDate = new Date();
                Date messageDate=new Date();
                try {
                    nowDate = sdf.parse(nowTime);
                    messageDate=sdf.parse(messageTime);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                long diff = nowDate.getTime() - messageDate.getTime();

                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                String elapsedTime="";
                if(diffDays >0)
                {
                    elapsedTime=diffDays+ " Days ago";
                }
                else if(diffHours>0)
                {
                    elapsedTime=diffHours+ " hours ago";
                }else if(diffMinutes > 0)
                {
                    elapsedTime=diffMinutes+ " min ago";
                }else
                {
                    elapsedTime=diffSeconds+ " sec ago";
                }


              //  String elpasedTime=diffDays+" Days, "+diffHours+" Hours "+diffMinutes+" min "+diffSeconds + " sec ago.";


                NotificationMessage notificationMessage=new NotificationMessage();
                notificationMessage.setMessage(cursor.getString(1));
                notificationMessage.setDatetime(elapsedTime);
                notificationList.add(notificationMessage);
               /* Contact contact = new Contact(cursor.getString(0));

                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);*/
            } while (cursor.moveToPrevious());
        }

        // return contact list
        return notificationList;
    }

}