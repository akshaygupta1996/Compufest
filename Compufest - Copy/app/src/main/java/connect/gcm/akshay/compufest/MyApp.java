package connect.gcm.akshay.compufest;

import android.app.Application;

/**
 * Created by Akshay on 24-07-2016.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "MONOSPACE", "fonts/CaviarDreams.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}

