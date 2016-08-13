package connect.gcm.akshay.compufest.Events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import connect.gcm.akshay.compufest.NavigationActivity;
import connect.gcm.akshay.compufest.R;

public class EventActivity extends NavigationActivity {

    private ImageButton workshop,cultural,technical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_event, null, false);
        drawer.addView(contentView, 0);

        workshop=(ImageButton) findViewById(R.id.btnEventWorkshops);
        cultural=(ImageButton) findViewById(R.id.btnEventCultural);
        technical=(ImageButton)findViewById(R.id.btnEventTechnical);

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        cultural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CulturalActivity.class));
            }
        });

        technical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TechnicalActivity.class));
            }
        });
    }
}
