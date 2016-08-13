package connect.gcm.akshay.compufest.Events;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import connect.gcm.akshay.compufest.R;

public class InfoActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    private static final String EXTRA_TRAVEL = "EXTRA_TRAVEL";
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.id)
    TextView id;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;
    Fragment6 fragment6;
    Fragment7 fragment7;
    Fragment8 fragment8;
    Fragment9 fragment9;
    Fragment10 fragment10;
    Fragment11 fragment11;
    Fragment12 fragment12;
    String event_id;


    public static Intent newInstance(Context context,Travel travel) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(EXTRA_TRAVEL, travel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

       Travel travel = getIntent().getParcelableExtra(EXTRA_TRAVEL);
        if (travel != null) {
            image.setImageResource(travel.getImage());
            id.setText(travel.getName());
            event_id=travel.getId();
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(event_id.equals("1")) {
            fragment1 = new Fragment1();
            transaction.add(R.id.layout, fragment1, "Fragment1");
            transaction.commit();
        }
        else if(event_id.equals("2")) {
            fragment2 = new Fragment2();
            transaction.add(R.id.layout, fragment2, "Fragment2");
            transaction.commit();

        }else if(event_id.equals("3")){
            fragment3 = new Fragment3();
            transaction.add(R.id.layout, fragment3, "Fragment3");
            transaction.commit();

        }else if(event_id.equals("4")){
            fragment4 = new Fragment4();
            transaction.add(R.id.layout, fragment4, "Fragment4");
            transaction.commit();

        }else if(event_id.equals("5")){
            fragment5 = new Fragment5();
            transaction.add(R.id.layout, fragment5, "Fragment5");
            transaction.commit();
        }else if(event_id.equals("6")){
            fragment6 = new Fragment6();
            transaction.add(R.id.layout, fragment6, "Fragment6");
            transaction.commit();
        }else if(event_id.equals("7")){
            fragment7 = new Fragment7();
            transaction.add(R.id.layout, fragment7, "Fragment7");
            transaction.commit();
        }else if(event_id.equals("8")){
            fragment8 = new Fragment8();
            transaction.add(R.id.layout, fragment8, "Fragment8");
            transaction.commit();
        }else if(event_id.equals("9")){
            fragment9 = new Fragment9();
            transaction.add(R.id.layout, fragment9, "Fragment9");
            transaction.commit();
        }else if(event_id.equals("10")){
            fragment10 = new Fragment10();
            transaction.add(R.id.layout, fragment10, "Fragment10");
            transaction.commit();
        }else if(event_id.equals("11")){
            fragment11 = new Fragment11();
            transaction.add(R.id.layout, fragment11, "Fragment11");
            transaction.commit();
        }else if(event_id.equals("12")){
            fragment12 = new Fragment12();
            transaction.add(R.id.layout, fragment12, "Fragment12");
            transaction.commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

