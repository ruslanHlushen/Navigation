package com.ruslan_hlushen.navigation.navigation.navigation_manager;

public interface INavigationManager {

    void startFragment(String nameFragment, Object data, boolean useAddTransaction);

    void startFragmentWithBackStackPosition(String nameFragment, int position, Object data, boolean useAddTransaction);

    void changeOnlyCurrentFragment(String nameFragment, Object data, boolean useAddTransaction);

    void returnToPreviousFragment();

    void returnToFragment(String nameFragment);

    void returnToFragmentWithResult(String nameFragment, Object data);

    void onExit();
}