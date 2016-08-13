package connect.gcm.akshay.compufest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactUs extends NavigationActivity {

    EditText edtName, edtEmail, edtCollege,edtmessage;
    String name, email, college,message;
    Button btnSend;
    RequestQueue requestQueue;
    StringRequest request;
    private static final String URL="http://ycce.hosting.acm.org/compufest/contactus.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        final View contentView = inflater.inflate(R.layout.activity_contact_us, null, false);
        drawer.addView(contentView, 0);

        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCollege = (EditText) findViewById(R.id.edtCollege);
        btnSend = (Button) findViewById(R.id.btnSend);
        edtmessage=(EditText)findViewById(R.id.editMessage);
        requestQueue = Volley.newRequestQueue(this);

        name = edtName.getText().toString();
        email = edtName.getText().toString();
        college = edtCollege.getText().toString();
        message=edtmessage.getText().toString();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Log.d("success", "Registration successful");
                                Toast.makeText(ContactUs.this, "" + jsonObject.getString("success"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), CompufestActivity.class));
                            } else {

                                Log.d("success", "Registration Unsuccessful");
                                Toast.makeText(ContactUs.this, "" + jsonObject.getString("error"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("fullname", name);
                        hashMap.put("email", email);
                        hashMap.put("college", college);
                        hashMap.put("message",message);
                        return hashMap;
                    }
                };

                requestQueue.add(request);


            }
        });


    }
}
