package net.thebestdealonline.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    public ProgressBar progress;
    Fragment selectedFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress=(ProgressBar)findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        progress.bringToFront();
        selectedFragment = new home();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_container,
                        selectedFragment)
                .commit();
        progress.setVisibility(View.GONE);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener
            navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(
                @NonNull MenuItem item)
        {
            progress.setVisibility(View.VISIBLE);
            home hom = new home();
            Deals deals=new Deals();
            fbgroup fb = new fbgroup();

            // By using switch we can easily get
            // the selected fragment
            // by using there id.

            switch (item.getItemId()) {
                case R.id.page_1:
                    selectedFragment = hom;
                    progress.bringToFront();


                    break;
                case R.id.page_2:
                    selectedFragment = deals;
                    progress.bringToFront();
                    break;
                case R.id.page_3:
                    selectedFragment = fb;
                    progress.bringToFront();
                    break;
            }
            // It will help to replace the one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragment_container,
                            selectedFragment)
                    .commit();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                  progress.setVisibility(View.GONE);
                }
            }, 3000);

            return true;

        }//progress.setVisibility(View.GONE);
    };
    int i=0;
    @Override
    public void onBackPressed() {


        if (i==1)
        {
            super.onBackPressed();
            i=0;
        }
        else
        {
            i++;
            Toast.makeText(this, "press back again to close app", Toast.LENGTH_SHORT).show();
        }

    }
}