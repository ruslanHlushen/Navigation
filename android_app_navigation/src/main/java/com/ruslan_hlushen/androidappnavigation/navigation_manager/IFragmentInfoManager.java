package com.ruslan_hlushen.androidappnavigation.navigation_manager;

import android.support.v4.app.Fragment;

/**
 * Created by Ruslan on 11.03.2017.
 */

public interface IFragmentInfoManager {

    Fragment getCurrentFragment();

    String getCurrentFragmentBackStackName();

    boolean isFragmentCurrent(String fragmentNameForBackStack);
}