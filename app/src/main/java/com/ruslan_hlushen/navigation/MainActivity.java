package com.ruslan_hlushen.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ruslan_hlushen.androidappnavigation.NavigationDependence;
import com.ruslan_hlushen.androidappnavigation.messages_manager.MessagesManager;
import com.ruslan_hlushen.androidappnavigation.navigation_manager.INavigationManager;
import com.ruslan_hlushen.androidappnavigation.navigation_manager.NavigationManager;
import com.ruslan_hlushen.navigation.fragments.FragmentA;
import com.ruslan_hlushen.navigation.fragments.FragmentB;
import com.ruslan_hlushen.navigation.fragments.FragmentC;
import com.ruslan_hlushen.navigation.fragments.FragmentD;
import com.ruslan_hlushen.navigation.fragments.FragmentF;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    NavigationDependence navigationDependence;

    INavigationManager navigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationApplication.getAppComponent().inject(this);

        navigationDependence.setManagers(initNavigationManager(), initMessagesManager());

        navigationManager = navigationDependence.getINavigationManager();

        navigationManager.startFragment(FragmentA.TAG, null, true);
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            navigationManager.onExit();
        }
    }


    private NavigationManager initNavigationManager() {

        return new NavigationManager(MainActivity.this, R.id.main_frame) {

            @Override
            public void onExit() {

                Toast.makeText(MainActivity.this, "onExit()", Toast.LENGTH_SHORT).show();
            }


            @Override
            public Fragment createFragment(String screenKey, Object data) {

                switch (screenKey) {

                    case FragmentA.TAG: {
                        return new FragmentA();
                    }
                    case FragmentB.TAG: {
                        return new FragmentB();
                    }
                    case FragmentC.TAG: {
                        return new FragmentC();
                    }
                    case FragmentD.TAG: {
                        return new FragmentD();
                    }
                    case FragmentF.TAG: {
                        return new FragmentF();
                    }
                    default:
                        return null;
                }
            }
        };
    }


    private MessagesManager initMessagesManager() {

        return new MessagesManager(MainActivity.this) {
            @Override
            public void showMessage(String message) {

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        };
    }
}