package com.ruslan_hlushen.androidappnavigation.navigation_manager;

import com.ruslan_hlushen.androidappnavigation.DataModel;

import java.util.List;

public interface INavigationManager extends IFragmentInfoManager {

    void startFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);

    void startFragmentWithBackStack(List<DataModel> dataModelList, boolean useAddTransaction);

    void startFragmentAndSetAtBackStackPosition(String fragmentNameForBackStack, int position, Object data, boolean useAddTransaction);

    void startFragmentAndSetAfter(String fragmentNameForBackStack,
                                  String fragmentNameToSetAfter,
                                  boolean clearBackStackIfWouldntFind,
                                  Object data,
                                  boolean useAddTransaction);

    void startFragmentAndSetBefore(String fragmentNameForBackStack,
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