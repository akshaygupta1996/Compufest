package connect.gcm.akshay.compufest;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CompufestActivity extends NavigationActivity {

    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;
    TextView txt_about,txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_compufest, null, false);
        drawer.addView(contentView, 0);
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        txt_about=(TextView)findViewById(R.id.txt_about);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/Caviar_Dreams_Bold.ttf");
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        txt_about.setTypeface(custom_font1);
        txt1.setTypeface(custom_font);
        txt2.setTypeface(custom_font);
        }



}
