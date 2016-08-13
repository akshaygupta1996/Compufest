package connect.gcm.akshay.compufest;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 24-07-2016.
 */
public class NotificationAdaptor extends BaseAdapter {

    Context context;
    List<NotificationMessage> arr_data=new ArrayList<>();
    LayoutInflater inflater;


    public NotificationAdaptor(Context context, List<NotificationMessage> arr_data) {
        this.context = context;
        this.arr_data = arr_data;
        Log.v("List",arr_data.toString());
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arr_data.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.notification_view,null);



        Typeface type = Typeface.createFromAsset(context.getAssets(),
                "fonts/CaviarDreams.ttf");

      //  Typeface custom_font = Typeface.createFromAsset(getAsset(), "fonts/CaviarDreams.ttf");
        TextView notification_message=(TextView)convertView.findViewById(R.id.notification_message);
        TextView notification_time=(TextView)convertView.findViewById(R.id.notification_time);
        notification_message.setTypeface(type);
        notification_message.setText(arr_data.get(position).getMessage());
        notification_time.setText(arr_data.get(position).getDatetime());
        return convertView;
    }

}
