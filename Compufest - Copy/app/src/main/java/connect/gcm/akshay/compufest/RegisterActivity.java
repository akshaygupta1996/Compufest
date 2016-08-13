package connect.gcm.akshay.compufest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterActivity extends NavigationActivity {

    EditText edtName,edtEmail,edtColege;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnRegister;
    String event_name;
    final static private String URL2="http://ycce.hosting.acm.org/compufest/register_app.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_register2, null, false);
        drawer.addView(contentView, 0);
        edtName=(EditText)findViewById(R.id.edtName);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtColege=(EditText)findViewById(R.id.edtCollege);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        requestQueue = Volley.newRequestQueue(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);
                event_name=radioButton.getText().toString();

                stringRequest = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Log.d("success", "Registration successful");
                                Toast.makeText(RegisterActivity.this, "" + jsonObject.getString("success"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), CompufestActivity.class));
                            } else {

                                Log.d("success", "Registration Unsuccessful");
                                Toast.makeText(RegisterActivity.this, "" + jsonObject.getString("error"), Toast.LENGTH_LONG).show();
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
                        hashMap.put("fullname", edtName.getText().toString());
                        hashMap.put("email", edtEmail.getText().toString());
                        hashMap.put("college", edtColege.getText().toString());
                        hashMap.put("event_name",event_name);
                        return hashMap;
                    }
                };

                requestQueue.add(stringRequest);


            }
        });

    }


}
