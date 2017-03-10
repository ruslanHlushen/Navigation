package com.ruslan_hlushen.navigation.navigation.navigation_manager;

public interface INavigationManager {

    void startFragment(String nameFragment, Object data);

    void startFragmentWithBackStackPosition(String nameFragment, int position, Object data);

    void changeOnlyCurrentFragment(String nameFragment, Object data);

    void returnToPreviousFragment();

    void returnToFragment(String nameFragment);

    void returnToFragmentWithResult(String nameFragment, Object data);

    void onExit();
}