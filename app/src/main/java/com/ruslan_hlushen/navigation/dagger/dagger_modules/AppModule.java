package com.ruslan_hlushen.navigation.dagger.dagger_modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ruslan on 05.03.2017.
 */

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;
    }


    @Provides
    @NonNull
    @Singleton
    public Context getAppContext() {
        return appContext;
    }
}