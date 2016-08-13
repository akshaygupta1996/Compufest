package connect.gcm.akshay.compufest.LeaderBoard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class LeaderBoardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LeaderBoardAdaptor leaderBoardAdaptor;
    public ArrayList<LeaderBoard> list=new ArrayList<LeaderBoard>();
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    final private String URL="http://ycce.hosting.acm.org/compufest/leaderboard_list.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);



        list=preparePostData();
        Log.v("LIST ",list.size()+"");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        leaderBoardAdaptor=new LeaderBoardAdaptor(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(LeaderBoardActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(leaderBoardAdaptor);
        Log.v("RECYCLER","CALLED");


       /* final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        final FoldingCell fc2=(FoldingCell)findViewById(R.id.folding_cell2);

        // attach click listener to folding cell
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });

        fc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc2.toggle(false);
            }
        });*/

    }

    private ArrayList<LeaderBoard> preparePostData() {


        //arr_post.add(new Post("", "", "", ""));

      // ArrayList<LeaderBoard> list;

        requestQueue = Volley.newRequestQueue(this);


        final ArrayList<LeaderBoard> finalList=new ArrayList<LeaderBoard>();
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", "Entered Response" + response.toString());

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {

                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String event_name,first,second,third;
                            event_name = jsonObject.getString("event_name");
                            first= jsonObject.getString("first");
                            second= jsonObject.getString("second");
                            third= jsonObject.getString("third");
                            Log.d("New Post Added ", "   " + event_name + " " + first + "   " + second+ "   " + third + "    " );
                            finalList.add(new LeaderBoard(event_name.toUpperCase() , first, second, third));

                            leaderBoardAdaptor.notifyDataSetChanged();


                        } catch (JSONException e) {

                            Log.d("Error", "Error Reading Response");
                            e.printStackTrace();
                        }
                    }
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
        return finalList;
    }

}
