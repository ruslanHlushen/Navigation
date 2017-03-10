package com.ruslan_hlushen.navigation;

import android.app.Application;

import com.ruslan_hlushen.navigation.dagger.AppComponent;
import com.ruslan_hlushen.navigation.dagger.DaggerAppComponent;
import com.ruslan_hlushen.navigation.dagger.dagger_modules.AppModule;

/**
 * Created by Ruslan on 05.03.2017.
 */

public class NavigationApplication extends Application {

    private static AppComponent appComponent;


    public static AppComponent getAppComponent() {

        return appComponent;
    }


    @Override
    public void onCreate() {

        super.onCreate();

        appComponent = buildComponents();
    }


    protected AppComponent buildComponents() {

        return DaggerAppComponent.builder()
                                 .appModule(new AppModule(this))
                                 .build();
    }
}