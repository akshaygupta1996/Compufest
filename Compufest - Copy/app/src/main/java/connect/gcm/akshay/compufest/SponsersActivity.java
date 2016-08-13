package connect.gcm.akshay.compufest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class SponsersActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsers);
        webView=(WebView)findViewById(R.id.web_view);

        webView.loadUrl("http://ycce.hosting.acm.org/Compufest2K16/Sponsors");
    }
}
