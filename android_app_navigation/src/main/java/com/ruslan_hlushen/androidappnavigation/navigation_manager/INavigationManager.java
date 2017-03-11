package com.ruslan_hlushen.androidappnavigation.navigation_manager;

public interface INavigationManager extends IFragmentInfoManager {

    void startFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);

    void startFragmentWithBackStackPosition(String fragmentNameForBackStack, int position, Object data, boolean useAddTransaction);

    void changeOnlyCurrentFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);

    void returnToPreviousFragment();

    void returnToFragment(String fragmentNameForBackStack);

    void returnToFragmentWithResult(String fragmentNameForBackStack, Object data);

    void onExit();
}