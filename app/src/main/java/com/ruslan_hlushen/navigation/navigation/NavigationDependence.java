package com.ruslan_hlushen.navigation.navigation;

import android.support.annotation.StringRes;

import com.ruslan_hlushen.navigation.navigation.messages_manager.IMessagesManager;
import com.ruslan_hlushen.navigation.navigation.navigation_manager.INavigationManager;

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


    public INavigationManager getINavigationManager() {
        return iNavigationManager;
    }


    public IMessagesManager getIMessagesManager() {
        return iMessagesManager;
    }
}