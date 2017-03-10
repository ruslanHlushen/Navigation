package com.ruslan_hlushen.navigation.dagger;

import com.ruslan_hlushen.navigation.MainActivity;
import com.ruslan_hlushen.navigation.dagger.dagger_modules.AppModule;
import com.ruslan_hlushen.navigation.dagger.dagger_modules.NavigationModule;
import com.ruslan_hlushen.navigation.fragments.FragmentA;
import com.ruslan_hlushen.navigation.fragments.FragmentB;
import com.ruslan_hlushen.navigation.fragments.FragmentC;
import com.ruslan_hlushen.navigation.fragments.FragmentD;
import com.ruslan_hlushen.navigation.fragments.FragmentF;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ruslan on 05.03.2017.
 */
@Component(modules = {AppModule.class, NavigationModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(FragmentD fragmentD);

    void inject(FragmentB fragmentB);

    void inject(FragmentC fragmentC);

    void inject(FragmentA fragmentA);

    void inject(FragmentF fragmentF);
}