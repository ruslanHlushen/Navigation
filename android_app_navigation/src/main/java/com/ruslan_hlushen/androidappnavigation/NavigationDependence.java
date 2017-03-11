package com.ruslan_hlushen.androidappnavigation;


import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.ruslan_hlushen.androidappnavigation.messages_manager.IMessagesManager;
import com.ruslan_hlushen.androidappnavigation.navigation_manager.INavigationManager;

/**
 * Created by Ruslan on 05.03.2017.
 */

public class NavigationDependence {

    INavigationManager iNavigationManager;
    IMessagesManager iMessagesManager;


    public NavigationDependence() {

        iNavigationManager = new INavigationManager() {
            @Override
            public void startFragment(String nameFragment, Object data, boolean useAddTransaction) { }

            @Override
            public void startFragmentWithBackStackPosition(String nameFragment, int position, Object data, boolean useAddTransaction) { }

            @Override
            public void changeOnlyCurrentFragment(String nameFragment, Object data, boolean useAddTransaction) { }

            @Override
            public void returnToPreviousFragment() { }

            @Override
            public void returnToFragment(String nameFragment) { }

            @Override
            public void returnToFragmentWithResult(String nameFragment, Object data) { }

            @Override
            public void onExit() { }

            @Override
            public Fragment getCurrentFragment() {
                return null;
            }

            @Override
            public String getCurrentFragmentBackStackName() {
                return null;
            }

            @Override
            public boolean isFragmentCurrent(String nameFragment) {
                return false;
            }
        };

        iMessagesManager = new IMessagesManager() {
            @Override
            public void showToast(String message, int length) { }

            @Override
            public void showToast(@StringRes int messageInResourcesId, int length) { }

            @Override
            public void showMessage(String message) { }

            @Override
            public void showMessage(@StringRes int messageInResourcesId) { }
        };
    }


    public void setNavigationManager(INavigationManager iNavigationManager) {

        this.iNavigationManager = iNavigationManager;
    }


    public void setMessagesManager(IMessagesManager iMessagesManager) {

        this.iMessagesManager = iMessagesManager;
    }


    public void setManagers(INavigationManager iNavigationManager, IMessagesManager iMessagesManager) {

        this.iNavigationManager = iNavigationManager;
        this.iMessagesManager = iMessagesManager;
    }


    public INavigationManager getINavigationManager() { return iNavigationManager; }


    public IMessagesManager getIMessagesManager() {return iMessagesManager; }
}