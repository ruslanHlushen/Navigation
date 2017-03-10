package com.ruslan_hlushen.navigation.navigation.messages_manager;

import android.support.annotation.StringRes;

/**
 * Created by Ruslan on 05.03.2017.
 */

public interface IMessagesManager {

    void showToast(String message, int length);

    void showToast(@StringRes int messageInResourcesId, int length);

    void showMessage(String message);

    void showMessage(@StringRes int messageInResourcesId);
}