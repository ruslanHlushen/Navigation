package com.ruslan_hlushen.androidappnavigation.navigation_manager;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ruslan_hlushen.androidappnavigation.DataModel;

import java.util.List;

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
    public void startFragmentAndSetAtBackStackPosition(String fragmentNameForBackStack,
                                                       int position,
                                                       Object data,
                                                       boolean useAddTransaction) {

        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= position; i--) {

            fragmentManager.popBackStack();
        }

        startFragment(fragmentNameForBackStack, data, useAddTransaction);
    }


    @Override
    public void startFragmentWithBackStack(List<DataModel> dataModelList, boolean useAddTransaction) {

        for (int i = 0; i < dataModelList.size(); i++) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment mainFragment = createFragment(dataModelList.get(i).getFragmentNameForBackStack(),
                                                   dataModelList.get(i).getData());

            if (mainFragment != null) {

                if (!useAddTransaction && (i > 0)) {
                    fragmentTransaction.remove(fragmentManager.findFragmentById(mainFrameLayoutId));
                }

                fragmentTransaction.add(mainFrameLayoutId, mainFragment);
                fragmentTransaction.addToBackStack(dataModelList.get(i).getFragmentNameForBackStack());
                fragmentTransaction.commit();

                if (!useAddTransaction) {
                    fragmentManager.executePendingTransactions();
                }
            }
        }
    }


    @Override
    public void startFragmentAndSetAfter(String fragmentNameForBackStack,
                                         String fragmentNameToSetAfter,
                                         boolean clearBackStackIfWouldntFind,
                                         Object data,
                                         boolean useAddTransaction) {

        boolean wasFind = false;

        if (isFragmentInBackStack(fragmentNameToSetAfter)) {

            fragmentManager.popBackStack(fragmentNameToSetAfter, 0);
            wasFind = true;
        }

        if (clearBackStackIfWouldntFind && (!wasFind)) {
            clearBackStack();
        }

        startFragment(fragmentNameForBackStack, data, useAddTransaction);
    }


    @Override
    public void startFragmentAndSetInstead(String fragmentNameForBackStack,
                                           String fragmentNameToSetInstead,
                                           boolean clearBackStackIfWouldntFind,
                                           Object data,
                                           boolean useAddTransaction) {

        boolean wasFind = false;

        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {

            if (fragmentNameToSetInstead.equals(fragmentManager.getBackStackEntryAt(i).getName())) {

                if (i - 1 >= 0) {
                    String fragmentNameToSetAfter = fragmentManager.getBackStackEntryAt(i - 1).getName();
                    fragmentManager.popBackStack(fragmentNameToSetAfter, 0);
                } else {
                    clearBackStack();
                }

                wasFind = true;
                break;
            }
        }

        if (clearBackStackIfWouldntFind && (!wasFind)) {
            clearBackStack();
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

        if (isFragmentInBackStack(fragmentNameForBackStack)) {

            fragmentManager.popBackStackImmediate(fragmentNameForBackStack, 0);
            fragmentManager.executePendingTransactions();
        }
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


    @Override
    public boolean isFragmentInBackStack(String fragmentNameForBackStack) {

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {

            if (fragmentNameForBackStack.equals(fragmentManager.getBackStackEntryAt(i).getName())) {
                return true;
            }
        }

        return false;
    }


    private void clearBackStack() {

        for (int j = 0; j < fragmentManager.getBackStackEntryCount(); j++) {
            fragmentManager.popBackStack();
        }
    }
}