package com.ruslan_hlushen.androidappnavigation.navigation_manager;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Ruslan on 01.03.2017.
 */

abstract public class NavigationManager implements INavigationManager {

    FragmentActivity fragmentActivity;
    FragmentManager fragmentManager;
    int mainFrameLayoutId;


    public NavigationManager() {}


    public NavigationManager(FragmentActivity fragmentActivity, @IdRes int mainFrameLayoutId) {

        this.fragmentActivity = fragmentActivity;
        this.fragmentManager = fragmentActivity.getSupportFragmentManager();
        this.mainFrameLayoutId = mainFrameLayoutId;
    }


    @Override
    public void startFragment(String nameFragment, Object data, boolean useAddTransaction) {

        Fragment mainFragment = createFragment(nameFragment, data);

        if (mainFragment != null) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (useAddTransaction) {
                fragmentTransaction.add(mainFrameLayoutId, mainFragment);
            } else {
                fragmentTransaction.replace(mainFrameLayoutId, mainFragment);
            }
                                                                     
            fragmentTransaction.addToBackStack(nameFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void startFragmentWithBackStackPosition(String nameFragment, int position, Object data, boolean useAddTransaction) {

        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= position; i--) {

            fragmentManager.popBackStack();
        }

        startFragment(nameFragment, data, useAddTransaction);
    }


    @Override
    public void changeOnlyCurrentFragment(String nameFragment, Object data, boolean useAddTransaction) {

        if (fragmentManager.getBackStackEntryCount() > 0) {

            fragmentManager.popBackStack();
        }

        startFragment(nameFragment, data, useAddTransaction);
    }


    @Override
    public void returnToPreviousFragment() {

        if (fragmentManager.getBackStackEntryCount() > 1) {

            fragmentManager.popBackStackImmediate();
            fragmentManager.executePendingTransactions();

        } else {
            onExit();
        }
    }


    @Override
    public void returnToFragment(String nameFragment) {

        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {

            if (nameFragment.equals(fragmentManager.getBackStackEntryAt(i).getName())) {

                fragmentManager.popBackStackImmediate(nameFragment, 0);
                break;
            }
        }

        fragmentManager.executePendingTransactions();
    }


    @Override
    public void returnToFragmentWithResult(String nameFragment, Object data) {

        returnToFragment(nameFragment);
        ((IOnFragmentResult) fragmentManager.findFragmentById(mainFrameLayoutId)).onFragmentResult(data);
    }


    abstract public Fragment createFragment(String screenKey, Object data);


    @Override
    public Fragment getCurrentFragment() {

        return fragmentManager.findFragmentById(mainFrameLayoutId);
    }


    @Override
    public String getCurrentFragmentBackStackName() {

        int backStackEntryCount = fragmentManager.getBackStackEntryCount();

        if (backStackEntryCount > 0) {
            return fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName();
        } else {
            return null;
        }
    }


    @Override
    public boolean isFragmentCurrent(String nameFragment) {

        return nameFragment.equals(getCurrentFragmentBackStackName());
    }
}