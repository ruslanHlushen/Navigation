package com.ruslan_hlushen.androidappnavigation.navigation_manager;

public interface INavigationManager extends IFragmentInfoManager {

    void startFragment(String nameFragment, Object data, boolean useAddTransaction);

    void startFragmentWithBackStackPosition(String nameFragment, int position, Object data, boolean useAddTransaction);

    void changeOnlyCurrentFragment(String nameFragment, Object data, boolean useAddTransaction);

    void returnToPreviousFragment();

    void returnToFragment(String nameFragment);

    void returnToFragmentWithResult(String nameFragment, Object data);

    void onExit();
}