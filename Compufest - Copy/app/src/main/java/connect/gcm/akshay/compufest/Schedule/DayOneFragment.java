package connect.gcm.akshay.compufest.Schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import connect.gcm.akshay.compufest.R;

/**
 * Created by Akshay on 22-07-2016.
 */
public class DayOneFragment extends ListFragment {
    ArrayList<Schedule> arr_list;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    ScheduleAdaptor scheduleAdaptor;
    private final String URL1="http://ycce.hosting.acm.org/compufest/schedule_day1.php";

    public DayOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_one, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        arr_list = new ArrayList<Schedule>();
        preparePostData();
        // arr_list.add(new Schedule(1,"Android App Development","Computer Lab 2","11 am"));
        scheduleAdaptor = new ScheduleAdaptor(getActivity(), arr_list);
        setListAdapter(scheduleAdaptor);
    }

    private void preparePostData() {

        requestQueue = Volley.newRequestQueue(getActivity());


        stringRequest = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", "Entered Response" + response.toString());

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {

                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String event_name, event_place, event_time;
                            String id, likes;
                            event_name = jsonObject.getString("event_name");
                            event_place = jsonObject.getString("event_place");
                            event_time = jsonObject.getString("event_time");
                            Log.d("New Schedule Added ", event_name + " " + event_place + "   " + event_time );
                            arr_list.add(new Schedule(16, event_name, event_place, event_time));
                            scheduleAdaptor.notifyDataSetChanged();


                        } catch (JSONException e) {

                            Log.d("Error", "Error Reading Response");
                            e.printStackTrace();
                        }
                    }
                    scheduleAdaptor.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  JSONArray jsonArray=new JSONArray(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }


}

