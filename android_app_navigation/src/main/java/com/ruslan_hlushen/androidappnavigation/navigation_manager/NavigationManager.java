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
    public void startFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction) {

        Fragment mainFragment = createFragment(fragmentNameForBackStack, data);

        if (mainFragment != null) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (useAddTransaction) {
                fragmentTransaction.add(mainFrameLayoutId, mainFragment);
            } else {
                fragmentTransaction.replace(mainFrameLayoutId, mainFragment);
            }

            fragmentTransaction.addToBackStack(fragmentNameForBackStack);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void startFragmentWithBackStackPosition(String fragmentNameForBackStack,
                                                   int position,
                                                   Object data,
                                                   boolean useAddTransaction) {

        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= position; i--) {

            fragmentManager.popBackStack();
        }

        startFragment(fragmentNameForBackStack, data, useAddTransaction);
    }


    @Override
    public void changeOnlyCurrentFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction) {

        if (fragmentManager.getBackStackEntryCount() > 0) {

            fragmentManager.popBackStack();
        }

        startFragment(fragmentNameForBackStack, data, useAddTransaction);
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
    public void returnToFragment(String fragmentNameForBackStack) {

        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {

            if (fragmentNameForBackStack.equals(fragmentManager.getBackStackEntryAt(i).getName())) {

                fragmentManager.popBackStackImmediate(fragmentNameForBackStack, 0);
                break;
            }
        }

        fragmentManager.executePendingTransactions();
    }


    @Override
    public void returnToFragmentWithResult(String fragmentNameForBackStack, Object data) {

        returnToFragment(fragmentNameForBackStack);
        ((IOnFragmentResult) fragmentManager.findFragmentById(mainFrameLayoutId)).onFragmentResult(data);
    }


    abstract public Fragment createFragment(String fragmentNameForBackStack, Object data);


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
    public boolean isFragmentCurrent(String fragmentNameForBackStack) {

        return fragmentNameForBackStack.equals(getCurrentFragmentBackStackName());
    }
}