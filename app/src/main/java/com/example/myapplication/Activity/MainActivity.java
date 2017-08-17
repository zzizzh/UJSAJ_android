package com.example.myapplication.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
=======
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.Fragment.LikeFragment;
import com.example.myapplication.Fragment.MyPageFragment;
import com.example.myapplication.PhysicalArchitecture.Client;
import com.example.myapplication.PhysicalArchitecture.ClientControl;
=======
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.CustomClass.ListViewAdapter;
import com.example.myapplication.Data.Posts;
import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.Fragment.LikeFragment;
import com.example.myapplication.Fragment.MyPageFragment;
import com.example.myapplication.PhysicalArchitecture.Client;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
import com.example.myapplication.R;
import com.example.myapplication.Fragment.SearchFragment;
import com.example.myapplication.Fragment.TimeLineFragment;

<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_SUCCESSS;

=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
/*
 * 2017.08.14 song
 */

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
    private BackPressCloseHandler backPressCloseHandler;
    ClientControl client;
    private MainActivity mainActivity;
=======
    Client client;
    private MainActivity mainActivity;
    private BackPressCloseHandler backPressCloseHandler;

>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
        CheckAppFirstExecute(); //is first run ?

        client = ClientControl.getClientControl();
=======
        CheckAppFirstExecute();


        try {
            Intent intent = getIntent();
            client = (Client) intent.getSerializableExtra("client");
        }catch (Exception e){
            client=null;
        }
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        mainActivity = this;
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        backPressCloseHandler = new BackPressCloseHandler(this);
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java

=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
                Intent intent = new Intent(mainActivity, WritingPostActivity.class);
=======
                Intent intent = new Intent(MainActivity.this, WritingPostActivity.class);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
                startActivity(intent);
            }
        });

    }

<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
    //앱최초실행확인 (true - 최초실행)
    public boolean CheckAppFirstExecute(){
        SharedPreferences pref = getSharedPreferences("IsFirst" , Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", false);
        if(!isFirst){ //if first run, go to HelpActivity
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(intent);
        }
        return !isFirst;
    }


    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //앱최초실행확인 (true - 최초실행)
    public boolean CheckAppFirstExecute(){
        SharedPreferences pref = getSharedPreferences("IsFirst" , Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", false);
        if(!isFirst){ //최초 실행시 true 저장
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();


        }

        return !isFirst;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
        if (id == R.id.change_pic_settings) {
            Intent intent = new Intent(this, ChangeUserPicActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

=======
        return super.onOptionsItemSelected(item);
    }
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
            Log.d("test", "position : " + position);

            if(position == 0)
                return new TimeLineFragment();
            else if(position == 1)
                return new SearchFragment(MainActivity.this);
            else if(position == 2)
                return new LikeFragment();
            else if(position == 3)
                return new MyPageFragment();
            else
               return null;

=======

            if (position == 0)
                //return new PostFragment();
                return new TimeLineFragment(client);
            else if (position == 1)
                return new SearchFragment(client,MainActivity.this);
            else if (position == 2)
                return new LikeFragment(client);
            else if (position == 3)
                return new MyPageFragment(client);
            else
                return null;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d:app/src/main/java/com/example/myapplication/Activity/MainActivity.java
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
                case 3:
                    return "";
            }
            return null;
        }
    }

}