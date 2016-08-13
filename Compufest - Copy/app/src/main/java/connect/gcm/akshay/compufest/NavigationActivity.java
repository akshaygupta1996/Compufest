package connect.gcm.akshay.compufest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import connect.gcm.akshay.compufest.Events.EventActivity;
import connect.gcm.akshay.compufest.LeaderBoard.LeaderBoardActivity;
import connect.gcm.akshay.compufest.Schedule.ScheduleActivity;
import connect.gcm.akshay.compufest.Teams.TeamsActivity;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.action_notification)
        {
            startActivity(new Intent(getApplicationContext(),NotificationActivity.class));

        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_events) {

            startActivity(new Intent(getApplicationContext(), EventActivity.class));
            // Handle the camera action
        }else if (id == R.id.nav_leaderboard) {
            startActivity(new Intent(getApplicationContext(), LeaderBoardActivity.class));

        } else if (id == R.id.nav_schedule) {
            startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));

        } else if (id == R.id.nav_map) {
            startActivity(new Intent(getApplicationContext(), MapActivity.class));

        } else if (id == R.id.nav_sponsers) {
            startActivity(new Intent(getApplicationContext(), SponsersActivity.class));

        } else if (id == R.id.nav_teams) {
            startActivity(new Intent(getApplicationContext(), TeamsActivity.class));
        } else if (id == R.id.nav_developers) {
            startActivity(new Intent(getApplicationContext(), DevelopersActivity.class));
        } else if (id == R.id.nav_register) {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        } else if (id == R.id.nav_contact_us) {
            startActivity(new Intent(getApplicationContext(), ContactUs.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
        public static Intent getOpenFacebookIntent(Context context) {

            try {
                context.getPackageManager()
                        .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("fb://page/376227335860239")); //Trys to make intent with FB's URI
            } catch (Exception e) {
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/karthikofficialpage")); //catches and opens a url to the desired page
            }
        }

        public static Intent getOpenTwitterIntent(Context context) {

            try {
                context.getPackageManager()
                        .getPackageInfo("com.twitter.android", 0); //Checks if Twitter is even installed.
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/drkarthiik")); //Trys to make intent with Twitter's's URI
            } catch (Exception e) {
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/drkarthiik")); //catches and opens a url to the desired page
            }
        }

        public static Intent getOpenLinkdinIntent(Context context) {

            try {
                context.getPackageManager()
                        .getPackageInfo("com.linkedin.android", 0); //Checks if Linkdin is even installed.
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/karthikm128")); //Trys to make intent with Linkdin's URI
            } catch (Exception e) {
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/karthikm128")); //catches and opens a url to the desired page
            }
        }

        public static Intent getOpenGPlusIntent(Context context) {

            try {
                context.getPackageManager()
                        .getPackageInfo("com.google.android.apps.plus", 0); //Checks if G+ is even installed.
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://plus.google.com/u/0/+KarthikM128")); //Trys to make intent with G+'s URI
            } catch (Exception e) {
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://plus.google.com/u/0/+KarthikM128")); //catches and opens a url to the desired page
            }
        }

    }

