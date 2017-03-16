package com.ruslan_hlushen.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ruslan_hlushen.androidappnavigation.DataModel;
import com.ruslan_hlushen.androidappnavigation.NavigationDependence;
import com.ruslan_hlushen.androidappnavigation.messages_manager.MessagesManager;
import com.ruslan_hlushen.androidappnavigation.navigation_manager.INavigationManager;
import com.ruslan_hlushen.androidappnavigation.navigation_manager.NavigationManager;
import com.ruslan_hlushen.navigation.fragments.FragmentA;
import com.ruslan_hlushen.navigation.fragments.FragmentB;
import com.ruslan_hlushen.navigation.fragments.FragmentC;
import com.ruslan_hlushen.navigation.fragments.FragmentD;
import com.ruslan_hlushen.navigation.fragments.FragmentF;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    NavigationDependence navigationDependence;

    INavigationManager iNavigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationApplication.getAppComponent().inject(this);

        navigationDependence.setManagers(initNavigationManager(), initMessagesManager());

        iNavigationManager = navigationDependence.getINavigationManager();

        List<DataModel> dataModels = new ArrayList<>();
        dataModels.add(new DataModel(FragmentA.TAG, null));
        dataModels.add(new DataModel(FragmentB.TAG, null));
        dataModels.add(new DataModel(FragmentC.TAG, null));

        iNavigationManager.startFragmentWithBackStack(dataModels, false);
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            iNavigationManager.onExit();
        }
    }


    private NavigationManager initNavigationManager() {

        return new NavigationManager(MainActivity.this, R.id.main_frame) {

            @Override
            public void onExit() {

                Toast.makeText(MainActivity.this, "onExit()", Toast.LENGTH_SHORT).show();
            }


            @Override
            public Fragment createFragment(String fragmentNameForBackStack, Object data) {

                info();

                switch (fragmentNameForBackStack) {

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

    private void info() {

        List<String> listNames = new ArrayList<>();
        listNames.add(FragmentA.TAG);
        listNames.add(FragmentB.TAG);
        listNames.add(FragmentC.TAG);
        listNames.add(FragmentD.TAG);
        listNames.add(FragmentF.TAG);

        for (int i = 0; i < listNames.size(); i++) {
            Log.d("fragmentContainer",
                  String.valueOf(listNames.get(i) + iNavigationManager.isFragmentInBackStack(listNames.get(i))));
        }
    }
}