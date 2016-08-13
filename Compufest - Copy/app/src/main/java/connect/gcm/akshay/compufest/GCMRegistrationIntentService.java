package connect.gcm.akshay.compufest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.util.HashMap;
import java.util.Map;


public class GCMRegistrationIntentService extends IntentService {

    RequestQueue requestQueue;
    StringRequest stringRequest;
    static final private String URL="http://ycce.hosting.acm.org/compufest/gcm.php";

    public static final String REGISTRATION_SUCCESS="RegistrationSuccess";
    public static final String REGISTRATION_ERROR="RegistrationError";
    public static final String TAG="GCMTOKEN";

    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        RegisterGCM();
    }

    private void RegisterGCM()  {

        SharedPreferences sharedPreferences=getSharedPreferences("GCM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        Intent registrationComplete=null;
        String token=null;
        try{

            InstanceID instanceID=InstanceID.getInstance(getApplicationContext());
            token=instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.v("GcmRegiIntentService","token:"+token);
            registrationComplete=new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token",token);

            String oldToken = sharedPreferences.getString(TAG,"");

            if(!"".equals(token)&&!oldToken.equals(token))
            {

                saveTokenToServer(token);
                editor.putString(TAG,token);
                editor.commit();
            }else
            {
                Log.v("GCmRegistrationService","OldToken");
            }


        }catch(Exception e)
        {
            Log.v("GcmRegIntentService","Registration erreo");
            registrationComplete=new Intent(REGISTRATION_ERROR);
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void saveTokenToServer(String token)
    {
        Log.v("Token before function",token);
        HashMap<String,String> paramPost = new HashMap();
        //  paramPost.put("action","add");
        paramPost.put("token",token);
        try{
            Log.v("Before server Token:",paramPost.get("token").toString());
            //String msgResult = getStringResultFromService_POST(URL,paramPost);
            //Log.v("ServiceResponding",msgResult);
            getStringResultFromService(URL,paramPost);

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void getStringResultFromService(String URL, final HashMap<String,String> params){

        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.v("Response",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    /*public String getStringResultFromService_POST(String serviceURL, Map<String,String> params)
    {
        HttpURLConnection cnn=null;
        String line=null;
        URL url;

        try {
            url=new URL(serviceURL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL invalid: "+serviceURL);
        }

        StringBuilder bodyBuilder=new StringBuilder();
        Iterator<Map.Entry<String,String>> iterator = params.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String,String> param =iterator.next();
            bodyBuilder.append(param.getKey()).append('=').append(param.getValue());
            if(iterator.hasNext()){
                bodyBuilder.append('&');
            }
        }

        String body=bodyBuilder.toString();
        Log.v("AccessService","param:"+body);
        byte[] bytes=body.getBytes();
        try{
            cnn=(HttpURLConnection)url.openConnection();
            cnn.setDoOutput(true);
            cnn.setUseCaches(false);
            cnn.setFixedLengthStreamingMode(bytes.length);
            cnn.setRequestMethod("POST");
            cnn.setRequestProperty("Content-Type","aplication/x-www-form-urlencoded;charset-UTF-8");

            OutputStream outputStream=cnn.getOutputStream();
            outputStream.write(bytes);
            outputStream.close();

            int status = cnn.getResponseCode();
            if(status!=200)
            {
                throw new IOException("Post fail with error code :"+status);

            }
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(cnn.getInputStream()));
            StringBuilder stringBuilder=new StringBuilder();
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+'\n');
            }
            return stringBuilder.toString();

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;

        }

    }*/
}
