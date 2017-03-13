package com.ruslan_hlushen.androidappnavigation.navigation_manager;

public interface INavigationManager extends IFragmentInfoManager {

    void startFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);

    void startFragmentWithBackStack(Object data, boolean useAddTransaction, String... fragmentNamesForBackStack);

    void startFragmentWithBackStackPosition(String fragmentNameForBackStack, int position, Object data, boolean useAddTransaction);

    void startFragmentAfter(String fragmentNameForBackStack,
                            String fragmentNameToSetAfter,
                            boolean clearBackStackIfWouldntFind,
                            Object data,
                            boolean useAddTransaction);

    void startFragmentBefore(String fragmentNameForBackStack,
                             String fragmentNameToSetBefore,
                             boolean clearBackStackIfWouldntFind,
                             Object data,
                             boolean useAddTransaction);

    void changeOnlyCurrentFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);

    void returnToPreviousFragment();

    void returnToFragment(String fragmentNameForBackStack);

    void returnToFragmentWithResult(String fragmentNameForBackStack, Object data);

    void onExit();
}