package com.ruslan_hlushen.navigation.navigation.messages_manager;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Ruslan on 05.03.2017.
 */

abstract public class MessagesManager implements IMessagesManager {

    Context context;


    public MessagesManager(Context context) {

        this.context = context;
    }


    @Override
    public void showToast(String message, int length) {

        Toast.makeText(context, message, length).show();
    }


    @Override
    public void showToast(@StringRes int messageInResourcesId, int length) {

        showToast(context.getString(messageInResourcesId), length);
    }


    @Override
    public void showMessage(@StringRes int messageInResourcesId) {

        showMessage(context.getString(messageInResourcesId));
    }
}