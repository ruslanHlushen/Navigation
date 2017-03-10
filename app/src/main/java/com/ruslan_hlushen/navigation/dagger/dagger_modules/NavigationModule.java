package com.ruslan_hlushen.navigation.dagger.dagger_modules;

import android.support.annotation.NonNull;

import com.ruslan_hlushen.androidappnavigation.NavigationDependence;
import com.ruslan_hlushen.androidappnavigation.messages_manager.IMessagesManager;
import com.ruslan_hlushen.androidappnavigation.navigation_manager.INavigationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ruslan on 05.03.2017.
 */

@Module
public class NavigationModule {

    private NavigationDependence navigationDependence;


    public NavigationModule() {

        this.navigationDependence = new NavigationDependence();

    }


    @Provides
    @NonNull
    public NavigationDependence getNavigationDependence() {

        return navigationDependence;
    }


    @Provides
    @NonNull
    public INavigationManager getINavigationManager() {

        return navigationDependence.getINavigationManager();
    }


    @Provides
    @NonNull
    public IMessagesManager getIMessagesManager() {

        return navigationDependence.getIMessagesManager();
    }
}