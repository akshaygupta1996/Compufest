package connect.gcm.akshay.compufest.Schedule;

/**
 * Created by Akshay on 22-07-2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import connect.gcm.akshay.compufest.R;


public class ScheduleAdaptor extends BaseAdapter {


    Context context;
    ArrayList<Schedule> arr_data=new ArrayList<>();
    LayoutInflater inflater;

    public ScheduleAdaptor(Context context, ArrayList<Schedule> arr_data) {
        this.context = context;
        this.arr_data = arr_data;
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
        convertView=inflater.inflate(R.layout.schedule_list_view,null);

        TextView schedule_event_name=(TextView)convertView.findViewById(R.id.schedue_event_name);
        TextView schedule_event_place=(TextView)convertView.findViewById(R.id.schedule_event_place);
        TextView schedule_event_time=(TextView)convertView.findViewById(R.id.schedule_event_time);

        schedule_event_name.setText(arr_data.get(position).getEvent_name());
        schedule_event_place.setText(arr_data.get(position).getEvent_place());
        schedule_event_time.setText(arr_data.get(position).getEvent_time());
        return convertView;
    }
}
