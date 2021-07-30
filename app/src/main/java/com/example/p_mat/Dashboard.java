package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Dashboard extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // setting bottom navigation
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // adding menu items
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_engineering_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_organisation));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_tasks));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_account));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // initialise fragments
                Fragment fragment = null;
                switch (item.getId()){
                    case 1 :
                        fragment = new AssitantFragment();
                        break;
                    case 2 :
                        fragment = new OrganisationFragment();
                        break;
                    case 3 :
                        fragment = new TodoFragment();
                        break;
                    case 4 :
                        fragment = new NoticeFragment();
                        break;
                    case 5 :
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        // set notification count
        bottomNavigation.setCount(1, "10");
        // default
        bottomNavigation.show(2, true);

        // on click event
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // display toast
                Toast.makeText(getApplicationContext(), "You Clicked" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}